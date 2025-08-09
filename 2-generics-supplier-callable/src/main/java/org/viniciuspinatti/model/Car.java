package org.viniciuspinatti.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Car {
    private String brand;
    private String model;
    private LocalDate releaseDate;
    private Category category;
}
