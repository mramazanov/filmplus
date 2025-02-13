package ru.jabka.filmplus.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class UserRequest {
    private final String name;
    private final String email;
    private final String login;
    private final LocalDate birthDay;
}