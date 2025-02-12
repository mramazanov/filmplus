package ru.jabka.filmplus.model.film;

import lombok.*;
import ru.jabka.filmplus.model.Genre;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class FilmRequest {
    private String name;
    private String description;
    private LocalDate releaseDate;
    private long duration;
    private Genre genres;
}