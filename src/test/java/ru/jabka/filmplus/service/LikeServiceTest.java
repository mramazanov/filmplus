package ru.jabka.filmplus.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.like.Like;
import ru.jabka.filmplus.repository.LikeRepository;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    public void createLike_valid() {
        Like like = buildLike(1L, 2L);
        Mockito.when(likeRepository.insert(like)).thenReturn(like);
        Like createdLike = likeService.createLike(like);
        Assertions.assertEquals(like, createdLike);
        Mockito.verify(likeRepository).insert(like);
    }

    @Test
    public void shouldReturnError_whenUserIdIncorrect() {
        Like like = buildLike(null, 2L);
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class, () -> likeService.createLike(like)
        );
        Assertions.assertEquals("Укажите id пользователя", exception.getMessage());
    }

    private Like buildLike(Long userId, Long filmId) {
        return Like.builder()
                .userId(userId)
                .filmId(filmId)
                .build();
    }

}
