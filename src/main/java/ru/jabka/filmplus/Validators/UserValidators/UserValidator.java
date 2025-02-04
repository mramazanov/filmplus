package ru.jabka.filmplus.Validators.UserValidators;

import ru.jabka.filmplus.model.user.UserRequest;

interface UserValidator {
    void validate(UserRequest user);
}