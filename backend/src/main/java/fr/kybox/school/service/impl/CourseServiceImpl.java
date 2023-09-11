package fr.kybox.school.service.impl;

import fr.kybox.school.model.RoleEnum;
import fr.kybox.school.model.entity.Course;
import fr.kybox.school.repository.CourseRepository;
import fr.kybox.school.service.CourseService;
import fr.kybox.school.service.PersonService;
import fr.kybox.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PersonService personService;
    private final SubjectService subjectService;

    @Override
    public Mono<Course> findById(Long id) {
        return this.courseRepository.findById(id).log().flatMap(this::reachData);
    }

    @Override
    public Flux<Course> findAll() {
        return this.courseRepository.findAll().log()
                .delayElements(Duration.ofMillis(200)) // <- SOFT SIMULATION
                .flatMap(this::reachData);
    }

    @Override
    public Flux<Course> findBySubject(Long id) {
        return this.courseRepository.findBySubject(id).log().flatMap(this::reachData);
    }

    @Override
    public Flux<Course> findByTeacher(Long id) {
        return this.personService.findById(id)
                .log()
                .map(person -> {
                    if(person.getRole().getLabel().equals(RoleEnum.TEACHER.toString()))
                        return person;
                    else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }).flux()
                .flatMap(person -> Flux.from(this.courseRepository.findByTeacher(person.getId())))
                .delayElements(Duration.ofMillis(200)) // <- SOFT SIMULATION
                .flatMap(this::reachData);
    }

    private Mono<Course> reachData(Course course){
        return this.personService.findById(course.getTeacherId())
                .zipWith(this.subjectService.findById(course.getSubjectId()))
                .map(result -> {
                    course.setTeacher(result.getT1());
                    course.setSubject(result.getT2());
                    return course;
                });
    }
}
