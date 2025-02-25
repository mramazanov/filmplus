package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.like.Like;
import ru.jabka.filmplus.service.FilmLikeService;

@RestController
@RequestMapping("api/v1/like")
@Tag(name = "Лайки")
public class FilmLikeController {

    private FilmLikeService filmLikeService;
    public FilmLikeController(FilmLikeService filmLikeService) {
        this.filmLikeService = filmLikeService;
    }
    @PostMapping
    @Operation(summary = "Добавление лайка фильму")
    public Like createLike(@RequestBody final Like like) {
        return filmLikeService.createLike(like);
    }
}