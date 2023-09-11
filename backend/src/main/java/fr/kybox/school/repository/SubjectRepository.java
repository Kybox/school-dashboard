package fr.kybox.school.repository;

import fr.kybox.school.model.entity.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SubjectRepository extends ReactiveCrudRepository<Subject, Long> {
}
