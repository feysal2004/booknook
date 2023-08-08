package com.techelevator.controller;


import com.techelevator.dao.ForumMessageDao;
import com.techelevator.model.ForumMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<ForumMessage> getListOfMessages(int topicId) {
        return forumMessageDao.getListOfMessages(topicId);
    }

}
