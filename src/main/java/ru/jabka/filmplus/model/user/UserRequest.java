package ru.jabka.filmplus.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String name;
    private String email;
    private String login;
    private LocalDate birthDay;
}