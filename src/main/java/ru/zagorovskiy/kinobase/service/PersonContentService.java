package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.PersonContent;

import java.util.List;

@Service
public interface PersonContentService {
    List<PersonContent> getAllByContentId(Long contentId);

    PersonContent create(PersonContent personContent);

    void delete(Long contentId, Long personId);
}
