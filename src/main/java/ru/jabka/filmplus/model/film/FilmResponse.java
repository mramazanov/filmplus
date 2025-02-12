package ru.jabka.filmplus.model.film;

import lombok.*;
import ru.jabka.filmplus.model.Genre;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class FilmResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private long duration;
    private Genre genres;
    private List<String> reviews;
    private int likes;
}