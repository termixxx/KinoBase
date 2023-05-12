package ru.zagorovskiy.kinobase.entity.enums;

public enum TypeOfContent {
    MOVIE("movie"),
    TV_SHOW("tv show"),
    ANIME("anime");
    private final String type;

    TypeOfContent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
