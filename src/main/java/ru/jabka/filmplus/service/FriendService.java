package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.friend.Friend;
import ru.jabka.filmplus.repository.FriendRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional(rollbackFor = Exception.class)
    public Friend addFriend(final Friend friend) {
        validate(friend);
        return friendRepository.insert(friend);
    }

    private void validate(Friend friendRequest) {
        if (friendRequest == null) {
            throw new BadRequestException("Введите информацию для дружбы");
        }
        if (Objects.equals(friendRequest.getUserId(), friendRequest.getFriendId())) {
            throw new BadRequestException(String.format("Пользователь с id = %d не может добавить сам себя", friendRequest.getUserId()));
        }
        if (friendRequest.getUserId() == null || friendRequest.getUserId() == 0) {
            throw new BadRequestException("Укажите id пользователя");
        }
        if (friendRequest.getFriendId() == null || friendRequest.getFriendId() == 0) {
            throw new BadRequestException("Укажите id друга");
        }
    }
}