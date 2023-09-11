package fr.kybox.school.repository;

import fr.kybox.school.model.entity.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
    @Query("SELECT * FROM person WHERE role_id = :1")
    Flux<Person> findByRoleId(Long roleId);
}
