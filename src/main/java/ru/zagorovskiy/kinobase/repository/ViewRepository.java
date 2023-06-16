package ru.zagorovskiy.kinobase.repository;

import org.springframework.stereotype.Repository;
import ru.zagorovskiy.kinobase.domain.entiti.View;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository {
    Optional<List<View>> findAllByProfileId(Long profileId);

    Optional<View> findByContentIdAndProfileId(Long contentId, Long profileId);

    void update(View view);

    void create(View view);

    void delete(Long contentId, Long profileId);

    void deleteAllByProfileId(Long profileId);

    void deleteAllByContentId(Long contentId);
}
