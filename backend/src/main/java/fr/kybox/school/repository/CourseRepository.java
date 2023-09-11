package fr.kybox.school.repository;

import fr.kybox.school.model.entity.Course;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {

    @Query("SELECT * FROM course WHERE subject_id = :1")
    Flux<Course> findBySubject(Long id);

    @Query("SELECT * FROM course WHERE teacher_id = :1")
    Flux<Course> findByTeacher(Long id);
}
