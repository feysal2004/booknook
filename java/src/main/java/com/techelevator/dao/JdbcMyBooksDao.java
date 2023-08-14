package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.MyBooks;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMyBooksDao implements MyBooksDao  {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMyBooksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MyBooks> getMyBooks() {
        List<MyBooks> myBookList = new ArrayList<>();
        String sql = "SELECT * FROM my_books ";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                MyBooks myBooks = mapRowToMyBook(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return myBookList;
    }

    @Override
    public MyBooks getBook(String book_name, String author) {
        String sql = "INSERT INTO my_books(books_name,author); " +
                "VALUES(?,?); ";

        return null;
    }




    private MyBooks mapRowToMyBook(SqlRowSet rs){
        MyBooks myBooks = new MyBooks();
        myBooks.setAuthor(rs.getString("book_name"));
        myBooks.setBook_name(rs.getString("author"));
        return myBooks;
    }


}
