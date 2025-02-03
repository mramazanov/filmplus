package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;

@Component
class FilmDescriptionValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if(!StringUtils.hasText(film.getDescription())) {
            throw new BadRequestException("Введите описание для фильма");
        }
    }
}