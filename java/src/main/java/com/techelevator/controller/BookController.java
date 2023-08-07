package com.techelevator.controller;


import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@CrossOrigin
@RequestMapping(path="/addBook")
@RestController
public class BookController {

    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addNewBook(@RequestBody Book newBook) {
        bookDao.addNewBook(newBook.getBookName(), newBook.getAuthor(), newBook.getAuthorSecond(), newBook.getDescription(), newBook.getSeries(), newBook.getRelease_date(), newBook.getDate_added_to_collection());
    }


}
