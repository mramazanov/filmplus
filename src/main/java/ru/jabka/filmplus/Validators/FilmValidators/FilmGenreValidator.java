package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.Genries;

import java.util.Arrays;

@Component
class FilmGenreValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if (genreIsEmpty(film)) {
            throw new BadRequestException("Введите информацию о жанре фильма");
        }

        film.getGenres().forEach(fg -> {
            Arrays.stream(Genries.values())
                    .filter(g -> fg.equalsIgnoreCase(g.toString()))
                    .findFirst()
                    .orElseThrow(() -> new BadRequestException("Указанные жанр(ы) фильма не найдены"));
        });
    }

    private boolean genreIsEmpty(FilmRequest film) {
        return film.getGenres() == null || film.getGenres().isEmpty();
    }
}