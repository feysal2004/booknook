package com.techelevator.model;

import java.util.List;

public class ForumMessage {

    public int message_id;
    public int topic_id;
    public String message_text;
    public String message_written_by;

    public ForumMessage(int message_id, int topic_id, String message_text, String message_written_by) {
        this.message_id = message_id;
        this.topic_id = topic_id;
        this.message_text = message_text;
        this.message_written_by = message_written_by;
    }

    public ForumMessage() {

    }
    

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getMessage_written_by() {
        return message_written_by;
    }

    public void setMessage_written_by(String message_written_by) {
        this.message_written_by = message_written_by;
    }

}
