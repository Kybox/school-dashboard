package fr.kybox.school.repository;

import fr.kybox.school.model.entity.Role;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long>  {

    @Query("SELECT id, label FROM role WHERE label = :1")
    Mono<Role> findByLabel(String label);
}
