package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
import com.techelevator.model.MyBook;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBookDao implements BookDao {


    private final JdbcTemplate jdbcTemplate;


    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public Book addNewBook(String book_name, String author, String author_second,
                           String description, String series, LocalDate release_date,
                           LocalDate date_added_to_collection) {
        String sql = "INSERT INTO books_table (book_name, author, author_second, description, series, release_date, date_added_to_collection)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING book_id";
        int newBookId = jdbcTemplate.queryForObject(sql, int.class, book_name, author, author_second, description, series, release_date, date_added_to_collection);
        Book newBook = new Book(book_name, author, author_second, description, series, newBookId, release_date, date_added_to_collection);
        return newBook;
    }




    @Override
    public MyBook addToBookShelf(MyBook myBook, User user) {

        String sql = "insert into my_books (book_name, author, user_id, username, isbn, thumbnail, description) values (?,?,?,?,?, ?, ?) ;";
        jdbcTemplate.update(sql, myBook.getBook_name(),myBook.getAuthor(),user.getId(),user.getUsername(),myBook.getIsbn(), myBook.getThumbnail(), myBook.getDescription());

        return myBook;
    }

    @Override
    public List<Book> getBooksFromDatabase() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books_table";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Book book = mapRowToBook(results);
                books.add(book);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return books;
    }

    @Override
    public List<MyBook> getMyBooksFromDatabase(int userId) {
        List<MyBook> myBooks = new ArrayList<>();
        String sql = "SELECT * FROM my_books WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                MyBook myBook = mapRowToMyBook(results);
                myBooks.add(myBook);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return myBooks;
    }

    @Override
    public void deleteMyBook(User user, MyBook myBook) {
        String sql = "DELETE FROM my_books where bookshelf_book_id = ?;";
        jdbcTemplate.update(sql);


    }

    private Book mapRowToBook(SqlRowSet rs) {
        Book book = new Book();
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setAuthorSecond(rs.getString("author_second"));
        book.setDescription(rs.getString("description"));
        book.setSeries(rs.getString("series"));
        book.setBook_id(rs.getInt("book_id"));
        book.setRelease_date(rs.getDate("release_date").toLocalDate());
        book.setDate_added_to_collection(rs.getDate("date_added_to_collection").toLocalDate());
        return book;
    }

    private MyBook mapRowToMyBook(SqlRowSet rs) {
        MyBook myBook = new MyBook();
        myBook.setAuthor(rs.getString("author"));
        myBook.setBook_name(rs.getString("book_name"));
        myBook.setDescription(rs.getString("description"));
        myBook.setThumbnail(rs.getString("thumbnail"));
        myBook.setIsbn(rs.getInt("isbn"));
        return myBook;
    }

}
