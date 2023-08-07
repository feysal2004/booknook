package com.techelevator.model;

import java.util.Date;

public class Book {
    public String title;
    public String author;
    public String authorSecond;
    public String description;
    public String series;
    public int book_id;
    public Date release_date;
    public Date date_added_to_collection;


    public Book(String title, String author, String authorSecond,
                String description, String series, int book_id, Date release_date,
                Date date_added_to_collection) {
        this.title = title;
        this.author = author;
        this.authorSecond = authorSecond;
        this.description = description;
        this.series = series;
        this.book_id = book_id;
        this.release_date = release_date;
        this.date_added_to_collection = date_added_to_collection;
    }

    public Book() {

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

    public String getAuthorSecond() {
        return authorSecond;
    }

    public void setAuthorSecond(String authorSecond) {
        this.authorSecond = authorSecond;
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

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getDate_added_to_collection() {
        return date_added_to_collection;
    }

    public void setDate_added_to_collection(Date date_added_to_collection) {
        this.date_added_to_collection = date_added_to_collection;
    }
}
