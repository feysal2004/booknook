package com.techelevator.controller;


import com.techelevator.dao.ForumMessageDao;
import com.techelevator.model.ForumMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@CrossOrigin
@RequestMapping(path="/topics/message")
@PreAuthorize("isAuthenticated()")
@RestController
public class ForumMessageController {

    private ForumMessageDao forumMessageDao;

    public ForumMessageController(ForumMessageDao forumMessageDao) {
        this.forumMessageDao = forumMessageDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path="/{topicId}", method = RequestMethod.GET)
    public List<ForumMessage> getListOfMessages(@PathVariable int topicId) {
        return forumMessageDao.getListOfMessages(topicId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path="/{topicId}/name", method = RequestMethod.GET)
    public String getTopicName(@PathVariable int topicId){
        return forumMessageDao.getTopicName(topicId);
    }


    @RequestMapping(path="/{topicId}", method = RequestMethod.POST)
    public ForumMessage createForumMessage(@RequestBody ForumMessage message, @PathVariable int topicId) {
        return forumMessageDao.createForumMessage(message, topicId);
    }


    @RequestMapping(path="/{messageId}", method = RequestMethod.PUT)
    public void  editForumMessage(@RequestBody ForumMessage message, @PathVariable int messageId, Principal principal){
        if (principal.getName().equals(message.message_written_by)) {
            forumMessageDao.editForumMessage(message, messageId);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable int id){
        forumMessageDao.deleteMessage(id);
    }


}
