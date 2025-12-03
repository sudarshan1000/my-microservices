package com.example.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String id;
    private String title;
    private String description;
    private String status;
    private String dueDate; // keep as String for now
}
