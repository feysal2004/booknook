package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.ForumMessage;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumMessageDao implements ForumMessageDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ForumMessage> getListOfMessages(int topicId) {
        List<ForumMessage> messages = new ArrayList<>();
        String sql = "SELECT message_text, topic_id FROM messages";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ForumMessage message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messages;
    }

    private ForumMessage mapRowToMessage(SqlRowSet rs) {
        ForumMessage forumMessage = new ForumMessage();
        forumMessage.setMessage_id(rs.getInt("message_id"));
        forumMessage.setTopic_id(rs.getInt("topic_id"));
        forumMessage.setMessage_text(rs.getString("message_text"));
        forumMessage.setMessage_written_by(rs.getString("message_written_by"));
        return forumMessage;
    }

}