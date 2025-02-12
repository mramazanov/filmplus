package ru.jabka.filmplus.model.like;

import lombok.Data;

@Data
public class Like {
    private final Long userId;
    private final Long filmId;
}