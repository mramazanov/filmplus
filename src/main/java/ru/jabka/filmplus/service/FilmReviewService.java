package ru.jabka.filmplus.service;

import org.springframework.stereotype.Service;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class FilmReviewService {

    private final Set<ReviewResponse> filmReviews = new HashSet<>();
    private final UserService userService;
    private final FilmService filmService;

    public FilmReviewService(final UserService userService, final FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }

    public ReviewResponse createFilmReview(final ReviewRequest reviewRequest) {
        validate(reviewRequest);
        userService.getUserById(reviewRequest.getIdUser());
        filmService.getFilmByid(reviewRequest.getIdFilm());

        filmReviews.stream()
                .filter(r -> Objects.equals(r.getIdUser(), reviewRequest.getIdUser())
                        && Objects.equals(r.getIdFilm(), reviewRequest.getIdFilm())
                )
                .findFirst()
                .ifPresent(
                        (r) -> { throw new BadRequestException(String.format("Пользователь с id %d уже оставил отзыв о фильме с id %d", r.getIdUser(), r.getIdFilm())); }
                );

        ReviewResponse filmReview = ReviewResponse.builder()
                .idUser(reviewRequest.getIdUser())
                .idFilm(reviewRequest.getIdFilm())
                .review(reviewRequest.getReview())
                .reviewDateTime(LocalDateTime.now())
                .build();

        filmReviews.add(filmReview);
        return filmReview;
    }

    private void validate(ReviewRequest reviewRequest) {
        if (reviewRequest == null) {
            throw new BadRequestException("Введите информацию для отзыва");
        }
        if (reviewRequest.getIdUser() == null) {
            throw new BadRequestException("Введите id пользователя");
        }
        if (reviewRequest.getIdFilm() == null) {
            throw new BadRequestException("Введите id фильма");
        }
    }
}