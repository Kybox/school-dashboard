package fr.kybox.school.service;

import fr.kybox.school.model.entity.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Mono<Course> findById(Long id);
    Flux<Course> findAll();
    Flux<Course> findBySubject(Long id);
    Flux<Course> findByTeacher(Long id);

}
