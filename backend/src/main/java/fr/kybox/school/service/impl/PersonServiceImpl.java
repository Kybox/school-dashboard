package fr.kybox.school.service.impl;

import fr.kybox.school.model.entity.Person;
import fr.kybox.school.model.entity.Role;
import fr.kybox.school.repository.PersonRepository;
import fr.kybox.school.repository.RoleRepository;
import fr.kybox.school.repository.SubjectRepository;
import fr.kybox.school.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Mono<Person> findById(Long id) {
        return this.personRepository.findById(id)
                .log()
                .flatMap(p -> Mono.just(p)
                        .zipWith(this.roleRepository.findById(p.getRoleId()))
                        .map(tuple -> {
                            tuple.getT1().setRole(tuple.getT2());
                            return tuple.getT1();
                        }));
    }

    @Override
    public Flux<Person> findByRole(String label) {
        var roleRef = new AtomicReference<Role>();
        return this.roleRepository.findByLabel(label)
                .log()
                .map(role -> {
                    roleRef.set(role);
                    return role;
                }).flux()
                .flatMap(role -> this.personRepository.findByRoleId(role.getId()))
                .delayElements(Duration.ofMillis(200)) // <- SOFT SIMULATION
                .map(person -> {
                    person.setRole(roleRef.get());
                    return person;
                })
                .flatMap(person -> {
                    if(person.getSubjectId() != null)
                    return this.subjectRepository.findById(person.getSubjectId())
                            .map(subject -> {
                                person.setSubject(subject);
                                return person;
                            });
                    else return Mono.just(person);
                });
    }
}
