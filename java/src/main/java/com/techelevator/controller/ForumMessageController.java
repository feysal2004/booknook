package com.techelevator.controller;


import com.techelevator.dao.ForumMessageDao;
import com.techelevator.model.ForumMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin
@RequestMapping(path="/topics/message")
@RestController
public class ForumMessageController {

    private ForumMessageDao forumMessageDao;

    public ForumMessageController(ForumMessageDao forumMessageDao) {
        this.forumMessageDao = forumMessageDao;
    }

    @RequestMapping(path="/{topicId}", method = RequestMethod.GET)
    public List<ForumMessage> getListOfMessages(@PathVariable int topicId) {
        return forumMessageDao.getListOfMessages(topicId);
    }

    @RequestMapping(path="/{topicId}/name", method = RequestMethod.GET)
    public String getTopicName(@PathVariable int topicId){
        return forumMessageDao.getTopicName(topicId);
    }

    @RequestMapping(path="/{topicId}", method = RequestMethod.POST)
    public String createForumMessage(@RequestBody ForumMessage message, @PathVariable int topicId) {
        return null; //TODO return something real and build the function
    }


}
