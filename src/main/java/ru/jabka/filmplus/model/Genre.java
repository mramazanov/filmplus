package ru.jabka.filmplus.model;

public enum Genre {
    ACTION("ACTION"),
    HORROR("HORROR"),
    DRAMA("DRAMA"),
    COMEDY("COMEDY"),
    THRILLER("THRILLER"),
    ROMANCE("ROMANCE"),
    WESTERN("WESTERN"),
    FANTASY("FANTASY"),
    ROMAN("ROMAN"),
    EMPTY("");

    private final String genre;
    Genre(String genre) {
        this.genre = genre;
    }
}