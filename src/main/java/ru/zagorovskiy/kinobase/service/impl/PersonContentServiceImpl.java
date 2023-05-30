package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.PersonContentRepository;
import ru.zagorovskiy.kinobase.service.PersonContentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonContentServiceImpl implements PersonContentService {
    private final PersonContentRepository personContentRepository;

    @Override
    public List<PersonContent> getAllByContentId(Long contentId) {
        return personContentRepository.findAllByContentId(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Persons for content not found"));
    }

    @Override
    public PersonContent create(PersonContent personContent) {
        if (personContentRepository.findByContentIdAndPersonId(personContent.getContentId(), personContent.getPersonId()).isPresent()) {
            throw new IllegalStateException("Person in this content already exists.");
        }
        personContentRepository.create(personContent);
        return personContent;
    }

    @Override
    public void delete(Long contentId, Long personId) {
        personContentRepository.delete(contentId, personId);
    }
}
