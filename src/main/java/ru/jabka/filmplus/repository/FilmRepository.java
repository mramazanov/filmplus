package ru.jabka.filmplus.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;
import ru.jabka.filmplus.repository.mapper.FilmMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilmRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.movie (title, description, release_date, duration, genre)
            VALUES (:title, :description, :releaseDate, :duration, :genre)
            RETURNING *;
            """;

    private static final String UPDATE = """
            UPDATE filmplus.movie SET title=:title, description=:description, genre=:genre
            WHERE id=:id
            RETURNING *;
            """;

    private static final String SEARCH = """
            SELECT * FROM filmplus.movie
            WHERE title ILIKE :title
            OR description ILIKE CONCAT('%', :description, '%')
            OR genre LIKE :genre
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM filmplus.movie
            WHERE id=:id
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FilmMapper filmMapper;

    public FilmResponse insert(final FilmRequest request) {
        return jdbcTemplate.queryForObject(INSERT, filmToSql(null, request), filmMapper);
    }

    public FilmResponse update(final Long filmId, final FilmRequest request) {
        return jdbcTemplate.queryForObject(UPDATE, filmToSql(filmId,request), filmMapper);
    }

    public FilmResponse getById(final Long filmId) {
        return jdbcTemplate.queryForObject(GET_BY_ID, filmToSql(filmId, null), filmMapper);
    }

    public List<FilmResponse> search(final String name, final String description, final Genre genre) {
        FilmRequest filmRequest = FilmRequest.builder()
                .name(name)
                .description(description)
                .genre(genre)
                .build();

        return jdbcTemplate.query(SEARCH, filmToSql(null, filmRequest), filmMapper);
    }

    private MapSqlParameterSource filmToSql(final Long filmId, final FilmRequest filmRequest){
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", filmId);
        if(filmRequest != null) {
            params.addValue("title", filmRequest.getName());
            params.addValue("description", filmRequest.getDescription());
            params.addValue("releaseDate", filmRequest.getReleaseDate());
            params.addValue("duration", filmRequest.getDuration());
            params.addValue("genre", filmRequest.getGenre().toString());
        }

        return params;
    }
}