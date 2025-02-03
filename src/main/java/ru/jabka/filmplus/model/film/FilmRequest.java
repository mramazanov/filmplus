package ru.jabka.filmplus.model.film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FilmRequest {
    private long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private long duration;
    private List<String> genres;
}