package ru.jabka.filmplus.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FilmMapper implements RowMapper<FilmResponse> {

    @Override
    public FilmResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FilmResponse.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("title"))
                .description(rs.getString("description"))
                .releaseDate(rs.getTimestamp("release_date").toLocalDateTime().toLocalDate())
                .duration(rs.getLong(("duration")))
                .genre(Genre.valueOf(rs.getString("genre")))
                .build();
    }
}