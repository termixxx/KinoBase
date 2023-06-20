package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.PersonContentRepository;
import ru.zagorovskiy.kinobase.service.PersonContentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonContentServiceImpl implements PersonContentService {
    private final PersonContentRepository personContentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PersonContent> getAllByContentId(Long contentId) {
        log.info("Getting all person contents by content ID: " + contentId);
        return personContentRepository.findAllByContentId(contentId)
                .orElseThrow(() -> {
                    log.error("Person contents not found for content ID: " + contentId);
                    return new ResourceNotFoundException("Persons for content not found");
                });
    }

    @Override
    @Transactional
    public PersonContent create(PersonContent personContent) {
        log.info("Creating person content");
        if (personContentRepository.findByContentIdAndPersonId(personContent.getContentId(), personContent.getPersonId()).isPresent()) {
            log.error("Person in this content already exists.");
            throw new IllegalStateException("Person in this content already exists.");
        }
        personContentRepository.create(personContent);
        return personContent;
    }

    @Override
    @Transactional
    public void deleteAllByContentId(Long contentId) {
        log.info("Deleting all person contents by content ID: " + contentId);
        personContentRepository.deleteAllByContentId(contentId);
    }

    @Override
    @Transactional
    public void deleteAllByPersonId(Long personId) {
        log.info("Deleting all person contents by person ID: " + personId);
        personContentRepository.deleteAllByPersonId(personId);
    }
}
