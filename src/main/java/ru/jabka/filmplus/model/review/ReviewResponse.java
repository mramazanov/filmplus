package ru.jabka.filmplus.model.review;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReviewResponse {
    private Long idUser;
    private Long idFilm;
    private String review;
    private LocalDateTime reviewDateTime;
}
