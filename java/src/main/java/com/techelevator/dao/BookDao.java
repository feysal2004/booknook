package com.techelevator.dao;

import com.techelevator.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {

    List<Book> getBooks();





    Book addNewBook(String bookName, String author, String authorSecond,
                    String description, String series, LocalDate release_date,
                    LocalDate date_added_to_collection);


    List<Book> getBooksFromDatabase();
}
