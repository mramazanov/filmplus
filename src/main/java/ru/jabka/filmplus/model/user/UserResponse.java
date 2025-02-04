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
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String login;
    private LocalDate birthDay;
    private UserResponse friend;
}