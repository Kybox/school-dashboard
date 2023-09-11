package fr.kybox.school.router;

import fr.kybox.school.model.RoleEnum;
import fr.kybox.school.model.entity.Course;
import fr.kybox.school.model.entity.Person;
import fr.kybox.school.model.entity.Subscription;
import fr.kybox.school.service.CourseService;
import fr.kybox.school.service.PersonService;
import fr.kybox.school.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component("application-router")
@RequiredArgsConstructor
public class Router {

    private final PersonService personService;
    private final CourseService courseService;
    private final SubscriptionService subscriptionService;

    @Bean
    @CrossOrigin(origins = "http://localhost")
    public RouterFunction<ServerResponse> router(){
        return route()

                // Person
                .GET("/teachers",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromProducer(this.personService
                                        .findByRole(RoleEnum.TEACHER.toString()), Person.class)))
                .GET("/students",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromProducer(this.personService.findByRole(RoleEnum.STUDENT.toString()), Person.class)))

                // Course
                .GET("/courses",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromProducer(this.courseService.findAll(), Course.class)))
                .GET("/courses/{id}",
                        req -> ok().contentType(MediaType.APPLICATION_JSON)
                                .body(this.courseService.findById(Long.valueOf(req.pathVariable("id"))), Course.class))
                .GET("/courses/subject/{id}",
                        req -> ok().contentType(MediaType.APPLICATION_JSON)
                                .body(this.courseService.findBySubject(Long.valueOf(req.pathVariable("id"))), Course.class))
                .GET("/courses/teacher/{id}",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromProducer(this.courseService.findByTeacher(Long.valueOf(req.pathVariable("id"))), Course.class)))

                // Subscription
                .GET("/subscriptions",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromProducer(this.subscriptionService.findAll(), Subscription.class)))
                .POST("/subscription",
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(this.subscriptionService.create(req.bodyToMono(Subscription.class)), Subscription.class))
                .GET("/subscription/{id}",
                        req -> ok().body(this.subscriptionService.findById(Long.valueOf(req.pathVariable("id"))), Subscription.class))
                .DELETE("/subscription/{id}",
                        req -> ok().body(this.subscriptionService.deleteById(Long.valueOf(req.pathVariable("id"))), Void.class))
                .GET("/subscriptions/student/{id}",
                        req -> ok().body(this.subscriptionService.findByStudent(Long.valueOf(req.pathVariable("id"))), Subscription.class))
                .build();
    }
}
