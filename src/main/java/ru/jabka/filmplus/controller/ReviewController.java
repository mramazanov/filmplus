package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;
import ru.jabka.filmplus.service.ReviewService;

@RestController
@RequestMapping("api/v1/review")
@Tag(name = "Отзывы")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Добавление отзыва о фильме")
    public ReviewResponse createReview(@RequestBody final ReviewRequest review) {
        return reviewService.createReview(review);
    }
}