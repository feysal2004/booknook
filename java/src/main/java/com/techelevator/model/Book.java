package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    public String book_name;
    public String author;
    public String author_second;
    public String description;
    public String series;
    public int book_id;
    public LocalDate release_date;
    public LocalDate date_added_to_collection;


    public Book(String book_name, String author, String author_second,
                String description, String series, int book_id, LocalDate release_date,
                LocalDate date_added_to_collection) {
        this.book_name = book_name;
        this.author = author;
        this.author_second = author_second;
        this.description = description;
        this.series = series;
        this.book_id = book_id;
        this.release_date = release_date;
        this.date_added_to_collection = date_added_to_collection;
    }

    public Book() {

    }

    public String getBookName() {
        return book_name;
    }

    public void setBookName(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorSecond() {
        return author_second;
    }

    public void setAuthorSecond(String author_second) {
        this.author_second = author_second;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public LocalDate getDate_added_to_collection() {
        return date_added_to_collection;
    }

    public void setDate_added_to_collection(LocalDate date_added_to_collection) {
        this.date_added_to_collection = date_added_to_collection;
    }
}
