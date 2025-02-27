package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.friend.Friend;
import ru.jabka.filmplus.repository.FriendRepository;

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
        if (friendRequest.getIdUser() == null || friendRequest.getIdUser() == 0) {
            throw new BadRequestException("Укажите id пользователя");
        }
        if (friendRequest.getIdFriend() == null || friendRequest.getIdFriend() == 0) {
            throw new BadRequestException("Укажите id друга");
        }
    }
}