package ru.jabka.filmplus.model.film;

import lombok.Builder;
import lombok.Data;

import ru.jabka.filmplus.model.Genre;

import java.time.LocalDate;

@Builder
@Data
public class FilmResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Long duration;
    private Genre genre;
}