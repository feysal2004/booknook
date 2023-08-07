package com.techelevator.model;

public class Topic {

    private String topicName;
    private int topicId;

    public Topic(String topicName, int topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
    }

    public Topic() {

    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
