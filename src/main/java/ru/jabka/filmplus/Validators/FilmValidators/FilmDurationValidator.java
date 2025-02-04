package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;

@Component
class FilmDurationValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if (film.getDuration() == 0 || film.getDuration() < 0) {
            throw new BadRequestException("Введите информацию о длительности фильма");
        }
    }
}