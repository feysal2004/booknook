package com.techelevator.controller;


import com.techelevator.dao.BookDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.MyBook;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RequestMapping(path="/books")
@RestController
public class BookController {

    private BookDao bookDao;
    private UserDao userDao;

    public BookController(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/addBook", method = RequestMethod.POST)
    public void addNewBook(@RequestBody Book newBook) {

        bookDao.addNewBook(newBook.getBookName(), newBook.getAuthor(), newBook.getAuthorSecond(), newBook.getDescription(), newBook.getSeries(), newBook.getRelease_date(), newBook.getDate_added_to_collection());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path= "", method = RequestMethod.GET)
    public List<Book> getBooksFromDatabase() {
        return bookDao.getBooksFromDatabase();
    }

    @RequestMapping(path = "/myBookShelf", method = RequestMethod.POST)
    public void addBook(@RequestBody MyBook myBook, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        MyBook addBook = bookDao.addToBookShelf(myBook, user);
    }

//    @PreAuthorize("permitAll")
    @RequestMapping(path="/myBookShelf", method = RequestMethod.GET)
    public List<MyBook> getListOfMyBooks(Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        return bookDao.getMyBooksFromDatabase(userId);
    }

    @RequestMapping(path="/myBookShelf/unread", method = RequestMethod.GET)
    public List<MyBook> getListOfUnreadBooks(Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
<<<<<<< HEAD

=======
        int userId = user.getId();
        return bookDao.getUnreadBooksFromDatabase(userId);
    }
>>>>>>> 20799caae68a9e5e35769b2cf75a462f16d96158


    @RequestMapping(path="/myBookShelf", method = RequestMethod.DELETE)
    public void deleteMyBook(Principal principal, @RequestBody MyBook myBook){
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        int bookId = myBook.bookshelf_book_id;
        bookDao.deleteMyBook(userId, bookId);
    }

    @RequestMapping(path="/myBookShelf/{bookId}", method = RequestMethod.PUT)
    public void updateBookToRead(Principal principal, @PathVariable int bookId) {
        User user = userDao.getUserByUsername(principal.getName());
        int userId = user.getId();
        bookDao.changeBookToRead(userId, bookId);
    }




}
