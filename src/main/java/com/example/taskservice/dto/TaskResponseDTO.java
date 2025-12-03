package com.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private String id;
    private String title;
    private String description;
    private String status;
    private String dueDate;
}
