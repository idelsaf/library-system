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
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;

    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    @Size(min = 3, max = 100, message = "Author's name should be between 3 and 100 characters")
    private String author;

    @Min(value = 1000, message = "This is a modern library")
    @Max(value = 2024, message = "Only existing books are supported")
    private int writingYear;

}
