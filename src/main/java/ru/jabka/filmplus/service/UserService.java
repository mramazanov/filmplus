package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jabka.filmplus.Validators.UserValidators.ValidateUser;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {

    public static HashSet<UserResponse> users = new HashSet<>();
    private final ValidateUser validateUser;
    private final FilmService filmService;

    public UserResponse userCreate(final UserRequest requestUser) {
        validateUser.validate(requestUser);
        UserResponse userResponse = UserResponse.builder()
                .id((long) users.size() + 1)
                .name(requestUser.getName())
                .email(requestUser.getEmail())
                .login(requestUser.getLogin())
                .birthDay(requestUser.getBirthDay())
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

    public UserResponse update(final UserRequest user) {
        validateUser.validate(user);
        UserResponse existUser = getUserById(user.getId());
        if (existUser == null) {
            return null;
        }
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setLogin(user.getLogin());
        existUser.setBirthDay(user.getBirthDay());
        return existUser;
    }

    public UserResponse addFriend(final int userId, final int friendId) {
        UserResponse currentUser = getUserById(userId);
        if (currentUser == null) {
            return null;
        }
        UserResponse friendUser = getUserById(friendId);
        currentUser.setFriend(friendUser);
        return currentUser;
    }

    public void delete(final long id) {
        users.remove(getUserById(id));
    }
}