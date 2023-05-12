package ru.zagorovskiy.kinobase.entity.enums;

public enum Condition {
    WATCHED("watched"),
    WATCHING("watching"),
    PLANNED("planned");
    private final String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
