package ru.jabka.filmplus.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.like.Like;
import ru.jabka.filmplus.repository.mapper.LikeMapper;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.like (user_id, movie_id)
            VALUES (:user_id, :movie_id)
            RETURNING *;
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LikeMapper likeMaper;

    public Like insert(final Like like) {
        try {
            return jdbcTemplate.queryForObject(INSERT, likeToSql(like), likeMaper);
        } catch (DuplicateKeyException e) {
            throw new BadRequestException(
                    String.format("Пользователь с user_id = %d уже поставил лайк фильму с id = %d", like.getUserId(), like.getFilmId())
            );
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(
                    String.format("Пользователь с user_id = %d или фильм с id = %d не найден", like.getUserId(), like.getFilmId()));
        }
    }

    private MapSqlParameterSource likeToSql(final Like like){
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_id", like.getUserId());
        params.addValue("movie_id", like.getFilmId());

        return params;
    }
}