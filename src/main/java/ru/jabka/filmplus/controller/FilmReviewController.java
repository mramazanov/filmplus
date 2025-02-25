package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;
import ru.jabka.filmplus.service.FilmReviewService;

@RestController
@RequestMapping("api/v1/review")
@Tag(name = "Отзывы")
public class FilmReviewController {

    private final FilmReviewService filmReviewService;
    public FilmReviewController(FilmReviewService filmReviewService) {
        this.filmReviewService = filmReviewService;
    }

    @PostMapping
    @Operation(summary = "Добавление отзыва о фильме")
    public ReviewResponse createFilmReview(@RequestBody final ReviewRequest review) {
        return filmReviewService.createFilmReview(review);
    }
}