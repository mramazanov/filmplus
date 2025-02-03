package ru.jabka.filmplus.Validators.UserValidators;

import org.springframework.util.StringUtils;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

public class UserNameValidator implements UserValidator{
    @Override
    public void validate(UserRequest user) {
        if (!StringUtils.hasText(user.getName())) {
            throw new BadRequestException("Укажите имя пользователя");
        }
    }
}