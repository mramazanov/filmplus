package ru.jabka.filmplus.model.review;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReviewResponse {
    private final Long idUser;
    private final Long idFilm;
    private final String review;
    private final LocalDateTime reviewDateTime;
}