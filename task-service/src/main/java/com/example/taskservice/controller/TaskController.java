package com.example.taskservice.controller;

import com.example.taskservice.dto.TaskResponseDTO;
import com.example.taskservice.model.Task;
import com.example.taskservice.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /api/tasks  -> create task
    @PostMapping
    public TaskResponseDTO createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    // GET /api/tasks -> all tasks
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /api/tasks/{id} -> one task
    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    // GET /api/tasks/{id}/description -> description only
    @GetMapping("/{id}/description")
    public String getDescriptionById(@PathVariable String id) {
        return taskService.getDescriptionById(id);
    }

    // GET /api/tasks?status=PENDING
    @GetMapping(params = "status")
    public List<TaskResponseDTO> getTasksByStatus(@RequestParam String status) {
        return taskService.getTasksByStatus(status);
    }
}
