package com.techelevator.dao;

import com.techelevator.model.ForumMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public interface ForumMessageDao {

    List<ForumMessage> getListOfMessages(int topicId);

    String getTopicName(int topicId);

    ForumMessage createForumMessage(ForumMessage message, int topicId);
    void editForumMessage(ForumMessage message, int messageId);

    void deleteMessage(int messageId);
}
