package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.jabka.filmplus.model.film.FilmRequest;
import ru.jabka.filmplus.model.film.FilmResponse;
import ru.jabka.filmplus.service.FilmService;

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
    public FilmResponse getFilm(@PathVariable final long id) {
        return filmService.getFilmByid(id);
    }

    @PatchMapping
    @Operation(summary = "Обновление фильма")
    public FilmResponse updateFilm(@RequestBody final FilmRequest film) {
        return filmService.update(film);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление фильма")
    public void updateFilm(@PathVariable final long id) {
        filmService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Получение фильма по name")
    public FilmResponse getFilmByName(@RequestParam(value="name") final String name) {
        return filmService.findFilmByName(name);
    }
}