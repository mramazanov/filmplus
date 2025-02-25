package ru.jabka.filmplus.service;

import org.springframework.stereotype.Service;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.like.Like;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class FilmLikeService {

    private final Set<Like> filmsLikes = new HashSet<>();
    private final FilmService filmService;
    private final UserService userService;

    public FilmLikeService(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    public Like createLike(final Like like) {
        validate(like);

        filmService.getFilmByid(like.getFilmId());
        userService.getUserById(like.getUserId());

        filmsLikes.stream()
                .filter((l) -> Objects.equals(like, l))
                .findFirst()
                .ifPresent(
                        (l) ->  {
                            throw new BadRequestException(String.format("Пользователь с id %d уже поставил лайк фильму с id %d", l.getUserId(), l.getFilmId()));
                        }
                );

        filmsLikes.add(like);
        return like;
    }

    private void validate(Like like) {
        if (like == null) {
            throw new BadRequestException("Введите информацию для лайка");
        }
        if (like.getUserId() == null) {
            throw new BadRequestException("Укажите id пользователя");
        }
        if (like.getFilmId() == null) {
            throw new BadRequestException("Укажите id фильма");
        }
    }
}