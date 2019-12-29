package com.jere.test.automaticchart.model;

public class MessageContent {
    private String message;
    private boolean isUserSend;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUserSend() {
        return isUserSend;
    }

    public void setUserSend(boolean userSend) {
        isUserSend = userSend;
    }
}
