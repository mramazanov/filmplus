package ru.jabka.filmplus.Validators.UserValidators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.jabka.filmplus.model.user.UserRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
class UserValidationImpl implements ValidateUser {

    private final List<UserValidator> userValidators;

    @Override
    public void validate(UserRequest user) {
        userValidators.forEach(validator -> validator.validate(user));
    }
}