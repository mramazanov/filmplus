package ru.jabka.filmplus.Validators.UserValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

import java.time.LocalDate;

@Component
class UserBirthdayValidator implements UserValidator {
    @Override
    public void validate(UserRequest user) {
        if (user.getBirthDay() == null || user.getBirthDay().isAfter(LocalDate.now())) {
            throw new BadRequestException("Укажите корректную дату рождения");
        }
    }
}