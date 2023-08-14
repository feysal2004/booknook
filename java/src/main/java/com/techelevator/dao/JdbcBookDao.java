package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
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

}
