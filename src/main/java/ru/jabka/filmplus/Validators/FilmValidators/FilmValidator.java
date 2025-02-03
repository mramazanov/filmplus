package ru.jabka.filmplus.Validators.FilmValidators;

import ru.jabka.filmplus.model.film.FilmRequest;

interface FilmValidator {
    void filmValidate(FilmRequest film);
}
