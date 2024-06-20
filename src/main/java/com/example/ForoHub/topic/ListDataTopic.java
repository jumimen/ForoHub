package com.example.ForoHub.topic;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ListDataTopic(
        String title,
        String message,
        String dateCreation,
        String status,
        String author,
        String course
) {
    public ListDataTopic(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getDateCreation().toString(), topic.getStatus().toString(),topic.getAuthor(), topic.getCourse());
    }
}
