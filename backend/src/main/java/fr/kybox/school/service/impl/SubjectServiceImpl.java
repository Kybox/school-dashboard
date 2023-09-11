package fr.kybox.school.service.impl;

import fr.kybox.school.model.entity.Subject;
import fr.kybox.school.repository.SubjectRepository;
import fr.kybox.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    @Override
    public Mono<Subject> findById(Long id) {
        return this.subjectRepository.findById(id).log();
    }
}
