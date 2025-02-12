package ru.jabka.filmplus.model.review;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class ReviewRequest {
    private final Long idUser;
    private final Long idFilm;
    private final String review;
}