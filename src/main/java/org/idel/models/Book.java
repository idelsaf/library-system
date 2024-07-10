package org.idel.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    @Size(min = 3, max = 100, message = "Author's name should be between 3 and 100 characters")
    private String author;

    @Min(value = 1000, message = "This is a modern library")
    @Max(value = 2024, message = "Only existing books are supported")
    private int writingYear;

    public Book() {}

    public Book(String name, String author, int writingYear) {
        this.name = name;
        this.author = author;
        this.writingYear = writingYear;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWritingYear() {
        return writingYear;
    }

    public void setWritingYear( int writingYear) {
        this.writingYear = writingYear;
    }
}
