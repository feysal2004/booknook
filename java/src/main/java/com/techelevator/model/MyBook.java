package com.techelevator.model;

import java.time.LocalDate;

public class MyBook {
    public String book_name;
    public String author;
    public String description;
    public int isbn;

    public MyBook(String book_name, String author, String description, int isbn){
        this.book_name = book_name;
        this.author = author;
        this.description=description;
        this.isbn=isbn;


    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
