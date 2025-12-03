package com.example.reportservice.service;

import com.example.reportservice.dto.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ReportService {

    private final RestTemplate restTemplate;

    public ReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public TaskDTO fetchTaskFromTaskService(String id) {
        String url = "http://localhost:8080/api/tasks/" + id;

        try {
            ResponseEntity<TaskDTO> response =
                    restTemplate.getForEntity(url, TaskDTO.class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            // Task not found (404)
            return new TaskDTO(
                    null,
                    null,
                    "Task not found in Task-Service",
                    "NOT_FOUND",
                    null
            );
        } catch (ResourceAccessException e) {
            // Task service is DOWN (connection refused)
            return new TaskDTO(
                    null,
                    null,
                    "Task-Service is DOWN. Please try again later.",
                    "SERVICE_UNAVAILABLE",
                    null
            );

        } catch (Exception e) {
            // ANY OTHER ERROR
            return new TaskDTO(
                    null,
                    null,
                    "Unexpected error occurred: " + e.getMessage(),
                    "ERROR",
                    null
            );
        }
    }
}