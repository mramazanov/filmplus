package ru.jabka.filmplus.model.review;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewRequest {
    private final Long userId;
    private final Long filmId;
    private final String review;
}