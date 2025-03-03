package ru.jabka.filmplus.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.review.ReviewRequest;
import ru.jabka.filmplus.model.review.ReviewResponse;
import ru.jabka.filmplus.repository.mapper.ReviewMapper;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.review (user_id, movie_id, review_text)
            VALUES (:userid, :movieid, :reviewtext)
            RETURNING *;
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ReviewMapper reviewMapper;

    public ReviewResponse insert(final ReviewRequest reviewRequest) {
        try {
            return jdbcTemplate.queryForObject(INSERT, reviewToSql(reviewRequest), reviewMapper);
        } catch (DuplicateKeyException e) {
            throw new BadRequestException(
                    String.format("Пользователь с userId = %d уже оставил отзыв фильму с id = %d", reviewRequest.getUserId(), reviewRequest.getFilmId())
            );
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(
                    String.format("Пользователь с userId = %d или фильм с id = %d не найден", reviewRequest.getUserId(), reviewRequest.getFilmId())
            );
        }
    }

    private MapSqlParameterSource reviewToSql(ReviewRequest reviewRequest) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", reviewRequest.getUserId());
        params.addValue("movieid", reviewRequest.getFilmId());
        params.addValue("reviewtext", reviewRequest.getReview());

        return params;
    }
}