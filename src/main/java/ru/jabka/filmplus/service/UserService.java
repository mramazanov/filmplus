package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.model.userfriend.Userfriend;

import java.time.LocalDate;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    public static HashSet<UserResponse> users = new HashSet<>();
    public UserResponse userCreate(final UserRequest requestUser) {
        validate(requestUser);

        UserResponse userResponse = UserResponse.builder()
                .id((long) users.size() + 1)
                .name(requestUser.getName())
                .email(requestUser.getEmail())
                .login(requestUser.getLogin())
                .birthDay(requestUser.getBirthDay())
                .friends(new HashSet<>())
                .build();

        users.add(userResponse);
        return userResponse;
    }

    public UserResponse getUserById(final long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Не удалось найти пользователя с id %d", id)));
    }

    public UserResponse update(final long userId, final UserRequest user) {
        validate(user);
        UserResponse existUser = getUserById(userId);
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setLogin(user.getLogin());
        existUser.setBirthDay(user.getBirthDay());
        return existUser;
    }

    public UserResponse addFriend(final Userfriend userfriend) {
        if(userfriend.getIdUser().equals(userfriend.getIdFriend())) {
            throw new BadRequestException(String.format("Пользователь с id %d не может добавить самого себя", userfriend.getIdUser()));
        }
        UserResponse currentUser = getUserById(userfriend.getIdUser());
        UserResponse friendUser = getUserById(userfriend.getIdFriend());

        currentUser.getFriends().stream()
                .filter(id -> id.equals(friendUser.getId()))
                .findFirst()
                .ifPresent(id -> {
                    throw new BadRequestException(String.format("Пользователь с id %d уже дружит с пользователем c id %d", currentUser.getId(), friendUser.getId()));
                });

        currentUser.getFriends().add(friendUser.getId());
        return currentUser;
    }
    public void delete(final long id) {
        users.remove(getUserById(id));
    }

    private void validate(UserRequest request) {
        if (request == null) {
            throw new BadRequestException("Введите информацию о пользователе");
        }
        if (!StringUtils.hasText(request.getName())) {
            throw new BadRequestException("Укажите имя пользователя");
        }
        if (!StringUtils.hasText(request.getEmail())) {
            throw new BadRequestException("Укажите почту");
        }
        if (!StringUtils.hasText(request.getLogin())) {
            throw new BadRequestException("Укажите логин");
        }
        if (request.getBirthDay() == null || request.getBirthDay().isAfter(LocalDate.now())) {
            throw new BadRequestException("Укажите корректную дату рождения");
        }
    }
}