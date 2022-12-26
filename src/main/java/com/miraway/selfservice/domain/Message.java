package com.miraway.selfservice.domain;

public class Message {

    private String topic;

    private String data;

    private String message;

    public Message() {}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message {" + "data = '" + data + '\'' + ", message = '" + message + '\'' + '}';
    }
}
