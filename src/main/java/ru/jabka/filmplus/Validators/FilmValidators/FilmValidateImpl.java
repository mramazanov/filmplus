package ru.jabka.filmplus.Validators.FilmValidators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.jabka.filmplus.model.film.FilmRequest;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class FilmValidateImpl implements ValidateFilm {
    private final List<FilmValidator> filmValidators;
    @Override
    public void validateFilm(FilmRequest film) {
        filmValidators.forEach(e -> e.filmValidate(film));
    }
}