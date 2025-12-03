package com.example.reportservice.controller;

import com.example.reportservice.dto.TaskDTO;
import com.example.reportservice.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // GET /api/reports/task/{id}
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> getTaskFromTaskService(@PathVariable String id) {
        TaskDTO response= reportService.fetchTaskFromTaskService(id);
        if ("SERVICE_UNAVAILABLE".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }

        if ("NOT_FOUND".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if ("ERROR".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
