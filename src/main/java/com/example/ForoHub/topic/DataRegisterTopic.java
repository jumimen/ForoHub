package com.example.ForoHub.topic;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterTopic(
        @NotBlank(message = "Campo obligatorio")
        String title,
        @NotBlank(message= "Campo obligatorio")
        String message,
        @NotBlank(message = "Campo obligatorio")
        String author,
        @NotBlank(message = "Campo obligatorio")
        String course
) {
}
