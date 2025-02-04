package ru.jabka.filmplus.Validators.FilmValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;

@Component
class FilmReleaseDateValidator implements FilmValidator {
    @Override
    public void filmValidate(FilmRequest film) {
        if(film.getReleaseDate() == null){
            throw new BadRequestException("Введите корректную дату выхода фильма");
        }
    }
}