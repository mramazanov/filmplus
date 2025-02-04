package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;

@Component
class FilmNameValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if (!StringUtils.hasText(film.getName())) {
            throw new BadRequestException("Введите имя фильма");
        }
    }
}