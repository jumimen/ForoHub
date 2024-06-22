package com.example.ForoHub.topic;

public record ListDataTopic(
        Long id,
        String title,
        String message,
        String dateCreation,
        String status,
        String author,
        String course
) {
    public ListDataTopic(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getDateCreation().toString(), topic.getStatus().toString(),topic.getAuthor(), topic.getCourse());
    }
}
