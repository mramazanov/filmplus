package ru.jabka.filmplus.Validators.UserValidators;

import ru.jabka.filmplus.model.user.UserRequest;

public interface UserValidator {
    void validate(UserRequest user);
}
