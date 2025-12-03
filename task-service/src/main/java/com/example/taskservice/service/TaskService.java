package com.example.taskservice.service;

import com.example.taskservice.dto.TaskResponseDTO;
import com.example.taskservice.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    // In-memory list instead of DB
    private final List<Task> tasks = new ArrayList<>();

    // Create/save task
    public TaskResponseDTO addTask(Task task) {
        tasks.add(task);           // store in memory
        return convert(task);      // return DTO
    }

    // Get all tasks
    public List<TaskResponseDTO> getAllTasks() {
        return tasks.stream()
                .map(this::convert)
                .toList();
    }

    // Get one task by id
    public TaskResponseDTO getTaskById(String id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(this::convert)
                .orElse(null);
    }

    // Get description only
    public String getDescriptionById(String id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(Task::getDescription)
                .orElse("No task found with id: " + id);
    }

    // Filter by status
    public List<TaskResponseDTO> getTasksByStatus(String status) {
        return tasks.stream()
                .filter(t -> t.getStatus() != null && t.getStatus().equalsIgnoreCase(status))
                .map(this::convert)
                .toList();
    }

    // Mapper: Model → DTO
    private TaskResponseDTO convert(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}
