package ru.jabka.filmplus.model.friend;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Friend {
    private Long userId;
    private Long friendId;
}