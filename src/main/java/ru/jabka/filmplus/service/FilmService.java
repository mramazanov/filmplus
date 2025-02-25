package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    private static HashSet<FilmResponse> films = new HashSet<>();
    public FilmResponse filmCreate(final FilmRequest film) {
        validate(film);
        FilmResponse filmResponse = FilmResponse.builder()
                .id((long) films.size() + 1)
                .name(film.getName())
                .description(film.getDescription())
                .releaseDate(film.getReleaseDate())
                .duration(film.getDuration())
                .genres(film.getGenre())
                .reviews(new ArrayList<>())
                .build();

        films.add(filmResponse);
        return filmResponse;
    }

    public FilmResponse getFilmByid(Long id) {
        return films.stream()
                .filter(f -> Objects.equals(f.getId(), id))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Не удалось найти фильм по id = %d", id)));
    }

    public FilmResponse update(final long filmId, final FilmRequest film) {
        validate(film);

        FilmResponse foundFilm = getFilmByid(filmId);
        foundFilm.setDescription(film.getDescription());
        foundFilm.setName(film.getName());
        foundFilm.setGenres(film.getGenre());
        foundFilm.setDuration(film.getDuration());
        foundFilm.setReleaseDate(film.getReleaseDate());

        return foundFilm;
    }

    public void delete(final long id) {
        FilmResponse foundFilm = getFilmByid(id);
        films.remove(foundFilm);
    }

    public Set<FilmResponse> search(final String name, final String description, final Genre genre) {
        return films.stream().filter(
                f -> f.getName().contains(name)
                        || ((description != null) && f.getDescription().contains(description))
                        || f.getGenres().equals(genre)
        ).collect(Collectors.toSet());
    }
    
    private void validate(FilmRequest filmRequest) {
        if (filmRequest == null) {
            throw new BadRequestException("Введите информацию о фильме");
        }
        if (!StringUtils.hasText(filmRequest.getName())) {
            throw new BadRequestException("Введите имя фильма");
        }
        if (!StringUtils.hasText(filmRequest.getDescription())) {
            throw new BadRequestException("Введите описание для фильма");
        }
        if (filmRequest.getReleaseDate() == null) {
            throw new BadRequestException("Введите корректную дату выхода фильма");
        }
        if (filmRequest.getDuration() == 0 || filmRequest.getDuration() < 0) {
            throw new BadRequestException("Введите информацию о длительности фильма");
        }
        if (filmRequest.getGenre() == null) {
            throw new BadRequestException("Введите информацию о жанре фильма");
        }
    }
}