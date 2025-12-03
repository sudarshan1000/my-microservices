package com.example.taskservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Status is required")
    private String status; // PENDING, IN_PROGRESS, COMPLETED

    private String dueDate; // YYYY-MM-DD
}
