package ru.jabka.filmplus.Validators.UserValidators;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

public class UserBirthdayValidator implements UserValidator {
    @Override
    public void validate(UserRequest user) {
        if (user.getBirthDay() == null) {
            throw new BadRequestException("Укажите дату рождения");
        }
    }
}