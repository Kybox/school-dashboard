package fr.kybox.school.service;

import fr.kybox.school.model.entity.Subject;
import reactor.core.publisher.Mono;

public interface SubjectService {
    Mono<Subject> findById(Long id);
}
