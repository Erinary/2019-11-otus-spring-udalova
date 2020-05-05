package ru.otus.erinary.hw05.jdbclibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    private long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
