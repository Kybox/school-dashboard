package fr.kybox.school.repository;

import fr.kybox.school.model.entity.Subscription;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<Subscription, Long> {

    @Query("SELECT * FROM subscription WHERE person_id = :1")
    Flux<Subscription> findByStudent(Long id);
}
