package com.techelevator.model;

import java.time.LocalDate;

public class MyBook {
    public String book_name;
    public String author;
    public String description;
   // public int isbn;
    public String thumbnail;
    public int bookshelf_book_id;

    public MyBook(String book_name, String author, String description,  String thumbnail, int bookshelf_book_id){
        this.book_name = book_name;
        this.author = author;
        this.description=description;
       // this.isbn=isbn;
        this.thumbnail = thumbnail;
        this.bookshelf_book_id = bookshelf_book_id;


    }

    public MyBook() {

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

//    public int getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(int isbn) {
//        this.isbn = isbn;
//    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getBookshelf_book_id() {
        return bookshelf_book_id;
    }

    public void setBookshelf_book_id(int bookshelf_book_id) {
        this.bookshelf_book_id = bookshelf_book_id;
    }
}
