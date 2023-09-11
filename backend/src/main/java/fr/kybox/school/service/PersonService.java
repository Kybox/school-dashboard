package fr.kybox.school.service;

import fr.kybox.school.model.entity.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> findById(Long id);
    Flux<Person> findByRole(String label);
}
