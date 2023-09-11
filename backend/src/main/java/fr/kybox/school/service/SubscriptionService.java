package fr.kybox.school.service;

import fr.kybox.school.model.entity.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriptionService {
    Mono<Subscription> create(Mono<Subscription> subscription);
    Mono<Subscription> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<Subscription> findByStudent(Long id);
    Flux<Subscription> findAll();
}
