package ru.jabka.filmplus.Validators.UserValidators;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

public class UserNullValidator implements UserValidator{
    @Override
    public void validate(UserRequest user) {
        if (user == null) {
            throw new BadRequestException("Введите информацию о пользователе");
        }
    }
}