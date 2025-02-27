package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.Genre;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;
import ru.jabka.filmplus.service.FilmService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/film")
@Tag(name = "Фильмы")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    @Operation(summary = "Создать фильм")
    public FilmResponse createFilm(@RequestBody final FilmRequest film) {
        return filmService.filmCreate(film);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение фильма по id")
    public FilmResponse getFilm(@PathVariable final Long id) {
        return filmService.getFilmByid(id);
    }

    @PatchMapping
    @Operation(summary = "Обновление фильма")
    public FilmResponse updateFilm(@RequestParam final Long filmId, @RequestBody final FilmRequest film) {
        return filmService.update(filmId, film);
    }

    @GetMapping
    @Operation(summary = "Поиск фильмов")
    public List<FilmResponse> searchFilm(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "description", required = false, defaultValue = "default") final String description,
            @RequestParam final Optional<Genre> genre
    ) {
        return filmService.search(name, description, genre.orElse(Genre.EMPTY));
    }
}