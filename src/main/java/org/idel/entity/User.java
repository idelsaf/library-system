package org.idel.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    @Min(value = 1900, message = "You're too old :(")
    @Max(value = 2024, message = "You weren't born yet :(")
    private int birthYear;

    public User(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}
