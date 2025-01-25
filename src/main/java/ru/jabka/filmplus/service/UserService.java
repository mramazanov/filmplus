package ru.jabka.filmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.User;

import java.util.HashSet;

@Service
public class UserService {

    public static HashSet<User> users = new HashSet<User>();

    public User userCreate(final User user) {
        validate(user);
        user.setId((long) users.size() + 1);
        users.add(user);
        return user;
    }

    public User getUserById(final long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Не удалось найти пользователя с id %d", id)));
    }

    public User update(final User user) {
        validate(user);
        User existUser = getUserById(user.getId());
        if (existUser == null) {
            return null;
        }
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        return existUser;
    }

    public void delete(final long id) {
        users.remove(getUserById(id));
    }

    private void validate(final User user) {
        if (user == null) {
            throw new BadRequestException("Введите информацию о пользователе");
        }
        if(!StringUtils.hasText(user.getName())) {
            throw new BadRequestException("Укажите имя пользователя");
        }
        if(!StringUtils.hasText(user.getEmail())) {
            throw new BadRequestException("Укажите почту");
        }
    }
}