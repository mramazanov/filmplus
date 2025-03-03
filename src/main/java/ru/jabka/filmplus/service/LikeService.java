package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.like.Like;
import ru.jabka.filmplus.repository.LikeRepository;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Like createLike(final Like like) {
        validate(like);
        return likeRepository.insert(like);
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