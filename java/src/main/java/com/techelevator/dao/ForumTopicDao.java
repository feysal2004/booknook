package com.techelevator.dao;

import com.techelevator.model.Topic;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ForumTopicDao {

    List<Topic> getTopics();

    Topic getForumTopicById(int id);

    Topic createForumTopic(String topicName);

    void deleteForumTopic(int topicId);

//    String getTopicName(int topicId);
}
