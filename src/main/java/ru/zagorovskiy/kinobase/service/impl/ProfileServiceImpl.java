package ru.zagorovskiy.kinobase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;
import ru.zagorovskiy.kinobase.domain.exception.ResourceNotFoundException;
import ru.zagorovskiy.kinobase.repository.ProfileRepository;
import ru.zagorovskiy.kinobase.service.CommentService;
import ru.zagorovskiy.kinobase.service.ProfileService;
import ru.zagorovskiy.kinobase.service.RatingService;
import ru.zagorovskiy.kinobase.service.ViewService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    private final ViewService viewService;
    private final CommentService commentService;
    private final RatingService ratingService;

    @Override
    @Transactional(readOnly = true)
    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Profile getByLogin(String login) {
        return profileRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    @Transactional
    public Profile update(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.update(profile);
        return profile;
    }

    @Override
    @Transactional
    public Profile create(Profile profile) {
        if (profileRepository.findByLogin(profile.getLogin()).isPresent()) {
            throw new IllegalStateException("Profile already exists.");
        }
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.create(profile);
        profile.setId(profileRepository.findByLogin(profile.getLogin()).get().getId());
        return profile;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        viewService.deleteAllByProfileId(id);
        commentService.deleteAllByProfileId(id);
        ratingService.deleteAllByProfileId(id);
        profileRepository.delete(id);
    }
}
