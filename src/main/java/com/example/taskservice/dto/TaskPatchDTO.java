package com.example.taskservice.dto;

import lombok.Data;

@Data
public class TaskPatchDTO {

    private String title;
    private String description;
    private String status;
    private String dueDate;
    public String testCheck() { return "hello"; }

}

