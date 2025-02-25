package ru.jabka.filmplus.model.film;

import lombok.Builder;
import lombok.Data;

import ru.jabka.filmplus.model.Genre;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class FilmRequest {
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final Long duration;
    private final List<Genre> genre;
}