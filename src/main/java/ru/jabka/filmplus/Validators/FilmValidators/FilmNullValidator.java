package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;

@Component
class FilmNullValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if (film == null) {
            throw new BadRequestException("Введите информацию о фильме");
        }
    }
}