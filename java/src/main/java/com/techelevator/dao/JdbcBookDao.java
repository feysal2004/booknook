package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    public Book addBook() {
        return null;
    }

    @Override
    public Book addNewBook(String title, String author, String authorSecond, String description, String series, Date release_date, Date date_added_to_collection) {
        return null;
    }

    @Override
    public Book addNewBook(String title, String author, String authorSecond,
                           String description, String series, int book_id, Date release_date,
                           Date date_added_to_collection) {
        String sql = "INSERT INTO books_table (title, author, description, release_date)" +
                " VALUES (?, ?, ?, ?) RETURNING book_id";
        int newBookId = jdbcTemplate.queryForObject(sql, int.class, title, author, description, release_date);
        Book newBook = new Book(title, author, authorSecond, description, series, newBookId, release_date, date_added_to_collection);
        return null;
    }


}
