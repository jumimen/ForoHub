package com.example.ForoHub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record ResponseDataTopic(
        Long id,
        String title,
        String message,
        String dateCreation,
        String status,
        String author,
        String course
) {
    public ResponseDataTopic(Long id, String title, String message, String dateCreation, String status, String author, String course) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.dateCreation = dateCreation;
        this.status = status;
        this.author = author;
        this.course = course;
    }
}
