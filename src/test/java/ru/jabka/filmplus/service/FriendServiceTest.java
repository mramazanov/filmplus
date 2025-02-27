package ru.jabka.filmplus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.friend.Friend;
import ru.jabka.filmplus.repository.FriendRepository;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTest {

    @Mock
    private FriendRepository friendRepository;

    @InjectMocks
    private FriendService friendService;

    @Test
    public void createFriend_valid() {
        Friend friend = buildFriend(1L, 2L);
        Mockito.when(friendRepository.insert(friend)).thenReturn(friend);
        Friend createdFriend = friendService.addFriend(friend);
        Assertions.assertEquals(friend, createdFriend);
        Mockito.verify(friendRepository).insert(friend);
    }

    @Test
    public void shouldReturnError_whenUserIdIncorrect() {
        Friend friend = buildFriend(null, 2L);
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class, () -> friendService.addFriend(friend)
        );
        Assertions.assertEquals("Укажите id пользователя", exception.getMessage());
    }

    private Friend buildFriend(Long idUser, Long idFriend) {
        return Friend.builder()
                .idUser(idUser)
                .idFriend(idFriend)
                .build();
    }
}