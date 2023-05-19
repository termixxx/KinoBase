package ru.zagorovskiy.kinobase.domain.entiti;

import lombok.Data;
import ru.zagorovskiy.kinobase.domain.enums.Position;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
}
