package com.techelevator.dao;

import com.techelevator.model.MyBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcMyBooksDao implements MyBookDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMyBooksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
