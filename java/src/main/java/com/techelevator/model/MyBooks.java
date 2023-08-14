package com.techelevator.model;

public class MyBooks {
    public String book_name;
    public String author;

    public MyBooks(String book_name, String author){
        this.book_name = book_name;
        this.author = author;
    }

    public MyBooks(){

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


}
