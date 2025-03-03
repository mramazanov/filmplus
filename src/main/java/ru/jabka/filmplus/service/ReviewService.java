package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;
import ru.jabka.filmplus.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(rollbackFor = Exception.class)
    public ReviewResponse createReview(final ReviewRequest reviewRequest) {
        validate(reviewRequest);
        return reviewRepository.insert(reviewRequest);
    }

    private void validate(ReviewRequest reviewRequest) {
        if (reviewRequest == null) {
            throw new BadRequestException("Введите информацию для отзыва");
        }
        if (reviewRequest.getUserId() == null) {
            throw new BadRequestException("Введите id пользователя");
        }
        if (reviewRequest.getFilmId() == null) {
            throw new BadRequestException("Введите id фильма");
        }
    }
}