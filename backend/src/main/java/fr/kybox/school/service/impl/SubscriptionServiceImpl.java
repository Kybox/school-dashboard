package fr.kybox.school.service.impl;

import fr.kybox.school.model.entity.Subscription;
import fr.kybox.school.repository.SubscriptionRepository;
import fr.kybox.school.service.CourseService;
import fr.kybox.school.service.PersonService;
import fr.kybox.school.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CourseService courseService;
    private final PersonService personService;

    @Override
    public Mono<Subscription> create(Mono<Subscription> subscription) {
        return subscription.log()
                .flatMap(this.subscriptionRepository::save)
                .flatMap(this::reachData);
    }

    @Override
    public Mono<Subscription> findById(Long id) {
        return this.subscriptionRepository.findById(id)
                .log()
                .flatMap(this::reachData);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return this.subscriptionRepository.deleteById(id).log();
    }

    @Override
    public Flux<Subscription> findByStudent(Long id) {
        return this.subscriptionRepository.findByStudent(id)
                .log()
                .delayElements(Duration.ofMillis(200)) // <- SOFT SIMULATION
                .flatMap(this::reachData);
    }

    @Override
    public Flux<Subscription> findAll() {
        return this.subscriptionRepository.findAll()
                .log()
                .delayElements(Duration.ofMillis(200)) // <- SOFT SIMULATION
                .flatMap(this::reachData);
    }

    private Mono<Subscription> reachData(Subscription subscription){
        return this.courseService.findById(subscription.getCourseId())
                .log()
                .zipWith(this.personService.findById(subscription.getPersonId()))
                .map(tuple -> {
                    subscription.setCourse(tuple.getT1());
                    subscription.setPerson(tuple.getT2());
                    return subscription;
                });
    }
}
