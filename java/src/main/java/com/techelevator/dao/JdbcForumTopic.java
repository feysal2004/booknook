package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Topic;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumTopic implements ForumTopicDao {

    private final JdbcTemplate jdbcTemplate;
    
    public JdbcForumTopic(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();
        String sql = "SELECT topic_name FROM topics;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Topic topic = mapRowToTopic(results);
                topics.add(topic);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return topics;
    }

    @Override
    public Topic getForumTopicById(int id) {
        return null;
    }

    @Override
    public Topic createForumTopic(String topicName) {
        return null;
    }

    private Topic mapRowToTopic(SqlRowSet rs) {
        Topic topic = new Topic();
        topic.setTopicName(rs.getString("topic_name"));
        topic.setTopicId(rs.getInt("topic_id"));
        return topic;
    }
}
