package ru.zagorovskiy.kinobase.domain.enums;

public enum Position {
    ACTOR("actor"),
    DIRECTOR("director");
    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
