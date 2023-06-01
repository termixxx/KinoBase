package ru.zagorovskiy.kinobase.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zagorovskiy.kinobase.domain.entiti.Rating;
import ru.zagorovskiy.kinobase.service.RatingService;
import ru.zagorovskiy.kinobase.web.dto.entiti.RatingDto;
import ru.zagorovskiy.kinobase.web.dto.mappers.RatingMapper;
import ru.zagorovskiy.kinobase.web.dto.validation.OnCreate;
import ru.zagorovskiy.kinobase.web.dto.validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
@Validated
@Tag(name = "Rating controller", description = "Rating API")
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @GetMapping("/{contentId}")
    public List<RatingDto> getAllRatingsByContentId(@PathVariable Long contentId) {
        List<Rating> ratings = ratingService.getAllByContentId(contentId);
        return ratingMapper.toDto(ratings);
    }

    @PostMapping("/add")
    public RatingDto addRating(@Validated(OnCreate.class) @RequestBody RatingDto ratingDto) {
        Rating rating = ratingMapper.toEntity(ratingDto);
        Rating createdRating = ratingService.create(rating);
        return ratingMapper.toDto(createdRating);
    }

    @PutMapping("/update")
    public RatingDto updateRating(@Validated(OnUpdate.class) @RequestBody RatingDto ratingDto) {
        Rating rating = ratingMapper.toEntity(ratingDto);
        Rating updatedRating = ratingService.update(rating);
        return ratingMapper.toDto(updatedRating);
    }

    @DeleteMapping("/delete")
    public void deleteRating(@RequestBody RatingDto ratingDto) {
        ratingService.delete(ratingDto.getContentId(), ratingDto.getProfileId());
    }
}
