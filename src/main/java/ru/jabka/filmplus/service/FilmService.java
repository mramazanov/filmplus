package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.Validators.FilmValidators.ValidateFilm;
import ru.jabka.filmplus.model.film.FilmResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private static HashSet<FilmResponse> films = new HashSet<>();
    private final ValidateFilm validateFilm;

    public FilmResponse filmCreate(final FilmRequest film) {
        validateFilm.validateFilm(film);
        List<String> reviews = new ArrayList<>();
        FilmResponse filmResponse = new FilmResponse(
                (long) films.size() + 1,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getGenres(),
                reviews,
                0
        );
        films.add(filmResponse);
        return filmResponse;
    }

    public FilmResponse getFilmByid(long id) {
        return films.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Не удалось найти фильм по id = %d", id)));
    }

    public FilmResponse update(final FilmRequest film) {
        validateFilm.validateFilm(film);
        FilmResponse foundFilm = getFilmByid(film.getId());
        if (foundFilm == null) {
            return null;
        }
        foundFilm.setDescription(film.getDescription());
        foundFilm.setName(film.getName());
        foundFilm.setGenres(film.getGenres());
        foundFilm.setDuration(film.getDuration());
        foundFilm.setReleaseDate(film.getReleaseDate());
        return foundFilm;
    }

    public void delete(final long id) {
        FilmResponse foundFilm = getFilmByid(id);
        films.remove(foundFilm);
    }

    public FilmResponse findFilmByName(final String name) {
        return films.stream().filter(f -> f.getName()
                .equals(name))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Не удалось найти фильм по name = %s", name)));
    }

    public FilmResponse addLike(final int idFilm) {
        FilmResponse film = getFilmByid(idFilm);
        if(film == null) {
            return null;
        }
        film.setLikes(film.getLikes() + 1);
        return film;
    }

    public FilmResponse addReview(final int id, final String review) {
        FilmResponse film = getFilmByid(id);
        if(film == null) {
            return null;
        }
        film.getReviews().add(review);
        return film;
    }
}