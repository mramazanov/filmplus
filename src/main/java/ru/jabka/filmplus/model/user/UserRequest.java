package ru.jabka.filmplus.model.user;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class UserRequest {
    private String name;
    private String email;
    private String login;
    private LocalDate birthDay;
}