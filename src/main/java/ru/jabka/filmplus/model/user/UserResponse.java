package ru.jabka.filmplus.model.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String login;
    private LocalDate birthDay;
}