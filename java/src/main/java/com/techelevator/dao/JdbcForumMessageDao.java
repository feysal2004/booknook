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
        String sql = "SELECT message_text, message_id, topic_id, message_written_by FROM messages WHERE topic_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, topicId);
            while (results.next()) {
                ForumMessage message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messages;
    }

    @Override
    public String getTopicName(int topicId){
        String topicName = "";
        String sql = "SELECT topic_name FROM topics WHERE topic_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, topicId);
        while(results.next()){
            topicName = results.getString("topic_name");
        }
        return topicName;
    }

    @Override
    public ForumMessage createForumMessage(ForumMessage message, int topicId) {
        String sql = "INSERT INTO messages (topic_id, message_text) VALUES (?, ?) RETURNING message_id";
        int message_id = jdbcTemplate.queryForObject(sql, int.class, topicId, message.message_text);
        ForumMessage newMessage = new ForumMessage(message_id, topicId, message.message_text, message.message_written_by);
        return newMessage;
    }

    @Override
    public void  editForumMessage(ForumMessage message, int messageId) {
        String sql = "UPDATE messages SET message_text = ? WHERE message_id = ?;";
        jdbcTemplate.update(sql, message.message_text, messageId);
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