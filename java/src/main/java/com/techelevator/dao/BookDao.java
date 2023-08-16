package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.MyBook;
import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {

    List<Book> getBooks();





    Book addNewBook(String bookName, String author, String authorSecond,
                    String description, String series, LocalDate release_date,
                    LocalDate date_added_to_collection);



    MyBook addToBookShelf(MyBook myBook, User user);

    List<Book> getBooksFromDatabase();

    List<MyBook> getMyBooksFromDatabase(int userId);
}
