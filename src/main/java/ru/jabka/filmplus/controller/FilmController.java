package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.Set;

@RestController
@RequestMapping("api/v1/film")
@Tag(name = "Фильмы")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

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

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление фильма")
    public void updateFilm(@PathVariable final Long id) {
        filmService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Поиск фильмов")
    public Set<FilmResponse> search(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "description", required = false) final String description,
            @RequestParam(value = "genre", required = false) final Genre genre
    ) {
        return filmService.search(name, description, genre);
    }
}