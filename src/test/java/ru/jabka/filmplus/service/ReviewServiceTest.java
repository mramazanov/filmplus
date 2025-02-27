package ru.jabka.filmplus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;
import ru.jabka.filmplus.repository.ReviewRepository;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void createFriend_valid() {
        ReviewRequest reviewRequest = buildReviewRequest(1L, 2L, "Review text");
        LocalDateTime reviewTime = LocalDateTime.of(2025, 5, 12, 12,15);
        ReviewResponse reviewResponse = buildReviewResponse(reviewRequest, reviewTime);
        Mockito.when(reviewRepository.insert(reviewRequest)).thenReturn(reviewResponse);
        ReviewResponse review = reviewService.createReview(reviewRequest);
        Assertions.assertEquals(reviewResponse, review);
        Mockito.verify(reviewRepository).insert(reviewRequest);
    }

    @Test
    public void shouldReturnError_whenUserIdIncorrect() {
        ReviewRequest reviewRequest = buildReviewRequest(null, 2L, "Review Text");
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class, () -> reviewService.createReview(reviewRequest)
        );
        Assertions.assertEquals("Введите id пользователя", exception.getMessage());
    }

    private ReviewResponse buildReviewResponse(ReviewRequest reviewRequest, LocalDateTime reviewTime) {
        return ReviewResponse.builder()
                .idUser(reviewRequest.getIdUser())
                .idFilm(reviewRequest.getIdFilm())
                .review(reviewRequest.getReview())
                .reviewDateTime(reviewTime)
                .build();
    }

    private ReviewRequest buildReviewRequest(Long idUser, Long idFilm, String review) {
        return ReviewRequest.builder()
                .idUser(idUser)
                .idFilm(idFilm)
                .review(review)
                .build();
    }
}