package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.domain.enums.Condition;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.ViewRepository;
import ru.zagorovskiy.kinobase.service.ViewService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;

    @Override
    @Transactional(readOnly = true)
    public List<View> getAllByProfileId(Long profileId) {
        log.info("Getting all views by profile ID: " + profileId);
        return viewRepository.findAllByProfileId(profileId)
                .orElseThrow(() -> {
                    log.error("Views not found for profile ID: " + profileId);
                    return new ResourceNotFoundException("Views not found");
                });
    }

    @Override
    @Transactional
    public View update(View view) {
        log.info("Updating view with content ID: " + view.getContentId() + " and profile ID: " + view.getProfileId());
        viewRepository.update(view);
        return view;
    }

    @Override
    @Transactional
    public View create(View view) {
        log.info("Creating view");
        view.setAddedAt(LocalDate.now());
        if (view.getCondition() == null) {
            view.setCondition(Condition.WATCHING);
        }
        if (viewRepository.findByContentIdAndProfileId(view.getContentId(), view.getProfileId()).isPresent()) {
            throw new IllegalStateException("View already exists.");
        }
        viewRepository.create(view);
        return view;
    }

    @Override
    @Transactional
    public void delete(Long contentId, Long profileId) {
        log.info("Deleting view for content ID: " + contentId + " and profile ID: " + profileId);
        viewRepository.delete(contentId, profileId);
    }

    @Override
    @Transactional
    public void deleteAllByProfileId(Long profileId) {
        log.info("Deleting all views by profile ID: " + profileId);
        viewRepository.deleteAllByProfileId(profileId);
    }

    @Override
    @Transactional
    public void deleteAllByContentId(Long contentId) {
        log.info("Deleting all views by content ID: " + contentId);
        viewRepository.deleteAllByContentId(contentId);
    }
}
