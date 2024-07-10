package org.idel.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class User {
    private int id;

    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    @Min(value = 1900, message = "You're too old :(")
    @Max(value = 2024, message = "You weren't born yet :(")
    private int birthYear;

    public User() {}

    public User(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
