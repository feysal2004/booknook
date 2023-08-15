package com.techelevator.controller;


import com.techelevator.dao.ForumTopicDao;
import com.techelevator.model.Topic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin
@RequestMapping(path="/topics")
@RestController
@PreAuthorize("isAuthenticated()")

public class ForumTopicsController {

    private ForumTopicDao forumTopicDao;

    public ForumTopicsController(ForumTopicDao forumTopicDao) {
     this.forumTopicDao = forumTopicDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Topic> getListOfForumTopics() {
        return forumTopicDao.getTopics();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createNewForumTopic(@RequestBody Topic newTopic) {
        forumTopicDao.createForumTopic(newTopic.getTopicName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteForumTopic(@PathVariable int id) {
        forumTopicDao.deleteForumTopic(id);
    }


    @RequestMapping(path="/{topicId}/messages", method = RequestMethod.GET)
    public int getMessageCount(@PathVariable int topicId) {
        return forumTopicDao.getNumberOfMessages(topicId);
    }
}
