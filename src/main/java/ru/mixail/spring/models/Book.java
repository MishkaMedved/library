package ru.mixail.spring.models;

import javax.validation.constraints.*;

public class Book {
    private int id;
    @Size(min = 2, max = 100,message = "Минимум 2 символа, максимум 100")
    private String title;
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "Поле должно быть формата: Фамилия Имя")
    private String author;
    @Min(value = 1500, message = "Год не может быть меньше 1500")
    private int year;

    public Book() {

    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
