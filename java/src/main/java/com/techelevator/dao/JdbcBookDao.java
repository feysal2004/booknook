package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public Book addNewBook(String book_name, String author, String author_second,
                           String description, String series, LocalDate release_date,
                           LocalDate date_added_to_collection) {
        String sql = "INSERT INTO books_table (book_name, author, author_second, description, series, release_date, date_added_to_collection)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING book_id";
        int newBookId = jdbcTemplate.queryForObject(sql, int.class, book_name, author, author_second, description, series, release_date, date_added_to_collection);
        Book newBook = new Book(book_name, author, author_second, description, series, newBookId, release_date, date_added_to_collection);
        return newBook;
    }


}
