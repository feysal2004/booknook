package com.techelevator.controller;


import com.techelevator.dao.ForumTopicDao;
import com.techelevator.model.Topic;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@CrossOrigin
@RequestMapping(path="/topics")
@RestController
public class ForumTopicsController {

    private ForumTopicDao forumTopicDao;

    public ForumTopicsController(ForumTopicDao forumTopicDao) {
     this.forumTopicDao = forumTopicDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Topic> getListOfForumTopics() {
        return forumTopicDao.getTopics();
    }


}
