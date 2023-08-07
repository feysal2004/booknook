package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.Date;
import java.util.List;

public interface BookDao {

    List<Book> getBooks();

    Book addBook();

    Book addNewBook(String title, String author, String authorSecond,
                    String description, String series, Date release_date,
                    Date date_added_to_collection);

    Book addNewBook(String title, String author, String authorSecond,
                    String description, String series, int book_id, Date release_date,
                    Date date_added_to_collection);
}
