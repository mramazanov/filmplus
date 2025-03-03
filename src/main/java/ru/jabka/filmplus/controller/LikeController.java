package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.like.Like;
import ru.jabka.filmplus.service.LikeService;

@RestController
@RequestMapping("api/v1/like")
@Tag(name = "Лайки")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @Operation(summary = "Добавление лайка фильму")
    public Like createLike(@RequestBody final Like like) {
        return likeService.createLike(like);
    }
}