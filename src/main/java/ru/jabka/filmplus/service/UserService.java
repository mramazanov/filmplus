package ru.jabka.filmplus.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.repository.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public UserResponse userCreate(final UserRequest requestUser) {
        validate(requestUser);
        return userRepository.insert(requestUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(final long id) {
        return userRepository.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserResponse update(final long userId, final UserRequest user) {
        validate(user);
        UserResponse foundUser = getUserById(userId);
        return userRepository.update(foundUser.getId(), user);
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