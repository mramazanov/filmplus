package ru.jabka.filmplus.model.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserRequest {
    private final String name;
    private final String email;
    private final String login;
    private final LocalDate birthDay;
}