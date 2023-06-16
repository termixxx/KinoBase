package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
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
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;

    @Override
    @Transactional(readOnly = true)
    public List<View> getAllByProfileId(Long profileId) {
        return viewRepository.findAllByProfileId(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Views not found"));
    }

    @Override
    @Transactional
    public View update(View view) {
        viewRepository.update(view);
        return view;
    }

    @Override
    @Transactional
    public View create(View view) {
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
        viewRepository.delete(contentId, profileId);
    }

    @Override
    public void deleteAllByProfileId(Long profileId) {
        viewRepository.deleteAllByProfileId(profileId);
    }

    @Override
    public void deleteAllByContentId(Long contentId) {
        viewRepository.deleteAllByContentId(contentId);
    }
}
