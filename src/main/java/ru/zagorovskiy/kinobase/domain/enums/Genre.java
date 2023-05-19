package ru.zagorovskiy.kinobase.domain.enums;

public enum Genre {
    ACTION("action"),
    ADVENTURE("adventure"),
    COMEDY("comedy"),
    DRAMA("drama"),
    HORROR("horror"),
    THRILLER("thriller"),
    ROMANCE("romance"),
    DOCUMENTARY("documentary");
    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
