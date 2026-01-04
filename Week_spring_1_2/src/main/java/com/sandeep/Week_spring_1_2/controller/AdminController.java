package com.sandeep.Week_spring_1_2.controller;

import com.sandeep.Week_spring_1_2.dto.ApiResponseDTO;
import com.sandeep.Week_spring_1_2.dto.TaskResponseDTO;
import com.sandeep.Week_spring_1_2.repository.TaskRepository;
import com.sandeep.Week_spring_1_2.service.TaskMapper;
import com.sandeep.Week_spring_1_2.service.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskServices taskServices;

    public AdminController(TaskRepository repo, TaskMapper mapper,TaskServices taskServices) {
        this.taskRepository = repo;
        this.taskMapper = mapper;
        this.taskServices=taskServices;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tasks")
    public ResponseEntity<ApiResponseDTO<List<TaskResponseDTO>>> getAllTasks() {

        List<TaskResponseDTO> tasks =
                taskRepository.findAll()
                        .stream()
                        .map(taskMapper::toDto)
                        .toList();

        return ResponseEntity.ok(
                ApiResponseDTO.success("All tasks fetched", tasks)
        );
    }


    public List<TaskResponseDTO> getAllActiveTasks() {
        return taskRepository.findByDeletedFalse()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/tasks/{id}/restore")
    public ResponseEntity<ApiResponseDTO<String>> restoreTask(@PathVariable Long id) {

        taskServices.restoreTask(id);

        return ResponseEntity.ok(
                ApiResponseDTO.success("Task restored successfully", null)
        );
    }
}

