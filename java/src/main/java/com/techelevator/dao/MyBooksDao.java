package com.techelevator.dao;

import com.techelevator.model.MyBooks;

import java.util.List;

public interface MyBooksDao {
    List<MyBooks> getMyBooks();

    MyBooks getBook(String book_name,String author);



}
