package ru.zagorovskiy.kinobase.service.impl;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.kinobase.domain.entiti.View;
import ru.zagorovskiy.kinobase.service.ViewService;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {
    @Override
    public List<View> getAllByProfileId(Long profileId) {
        return null;
    }

    @Override
    public View update(View view) {
        return null;
    }

    @Override
    public View create(View view) {
        return null;
    }

    @Override
    public void delete(Long contentId, Long profileId) {

    }
}
