package ru.zagorovskiy.kinobase.service;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.Profile;

@Service
public interface ProfileService {
    Profile getById(Long id);

    Profile getByLogin(String login);

    Profile updatePassword(Profile profile);

    Profile updateNickname(Profile profile);

    Profile updateEmail(Profile profile);

    Profile create(Profile profile);

    void delete(Long id);

}
