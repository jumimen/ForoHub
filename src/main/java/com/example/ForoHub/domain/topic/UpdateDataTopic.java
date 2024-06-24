package com.example.ForoHub.domain.topic;



public record UpdateDataTopic(
        String title,
        String message,
        String dateCreation,
        String status,
        String author,
        String course ) {
    public UpdateDataTopic(String title, String message, String dateCreation, String status, String author, String course) {
        this.title = title;
        this.message = message;
        this.dateCreation = dateCreation;
        this.status = status;
        this.author = author;
        this.course = course;
    }
}


