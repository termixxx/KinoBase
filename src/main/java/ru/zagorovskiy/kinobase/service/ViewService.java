package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.View;

import java.util.List;

@Service
public interface ViewService {

    List<View> getAllByProfileId(Long profileId);

    View update(View view);

    View create(View view);

    void delete(Long contentId, Long profileId);

    void deleteAllByProfileId(Long profileId);

    void deleteAllByContentId(Long contentId);
}
