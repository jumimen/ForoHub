package com.example.ForoHub.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateDataTopic(
        String title,
        String message,
        String author,
        String course ) {
}
