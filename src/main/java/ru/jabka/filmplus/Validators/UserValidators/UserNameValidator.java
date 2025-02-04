package ru.jabka.filmplus.Validators.UserValidators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;

@Component
class UserNameValidator implements UserValidator{
    @Override
    public void validate(UserRequest user) {
        if (!StringUtils.hasText(user.getName())) {
            throw new BadRequestException("Укажите имя пользователя");
        }
    }
}