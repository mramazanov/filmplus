package ru.jabka.filmplus.Validators.UserValidators;

import org.springframework.stereotype.Component;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

@Component
class UserNullValidator implements UserValidator{
    @Override
    public void validate(UserRequest user) {
        if (user == null) {
            throw new BadRequestException("Введите информацию о пользователе");
        }
    }
}