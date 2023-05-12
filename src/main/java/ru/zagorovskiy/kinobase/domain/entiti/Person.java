package ru.zagorovskiy.kinobase.entity;

import lombok.Data;
import ru.zagorovskiy.kinobase.entity.enums.Position;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
}
