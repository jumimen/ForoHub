package com.example.ForoHub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotBlank
        String course
) {
}
