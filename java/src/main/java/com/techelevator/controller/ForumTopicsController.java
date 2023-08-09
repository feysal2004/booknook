package com.techelevator.controller;


import com.techelevator.dao.ForumTopicDao;
import com.techelevator.model.Topic;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void createNewForumTopic(@RequestBody Topic newTopic) {
        forumTopicDao.createForumTopic(newTopic.getTopicName());
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public void deleteForumTopic(@RequestBody Topic topic) {
        forumTopicDao.deleteForumTopic(topic.getTopicId());
    }

//    @RequestMapping(path= "/name/${topicId}")
//    public String getTopicName(@PathVariable int topicId) {
//        return forumTopicDao.getTopicName(topicId);
//    }

}
