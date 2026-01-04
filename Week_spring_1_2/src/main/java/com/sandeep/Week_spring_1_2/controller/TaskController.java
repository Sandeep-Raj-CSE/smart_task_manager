package com.sandeep.Week_spring_1_2.controller;


import com.sandeep.Week_spring_1_2.dto.ApiResponseDTO;
import com.sandeep.Week_spring_1_2.dto.TaskRequestDTO;
import com.sandeep.Week_spring_1_2.dto.TaskResponseDTO;
import com.sandeep.Week_spring_1_2.entity.TaskEntity;
import com.sandeep.Week_spring_1_2.service.TaskServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task APIs")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskServices taskServices;

    // create

    @Operation(summary = "Create new task")
    @PostMapping
//    public TaskEntity createTask(@RequestBody TaskEntity taskEntity){
//        log.info("task is creating");
//        return taskServices.createTask(taskEntity);
//
//    }
    public ResponseEntity<ApiResponseDTO<TaskResponseDTO>> createTask(
           @Valid @RequestBody TaskRequestDTO dto) {

        TaskResponseDTO response = taskServices.createTask(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success("Task created successfully", response));
    }


    // get the task
    @GetMapping
    public List<TaskEntity> getAllTask(){
        return taskServices.getAllTask();
    }


    // read by id

    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable Long id){
        return taskServices.getTaskById(id);
    }


    // update by id

    @PutMapping("/{id}")
    public TaskEntity updatedTask(@PathVariable Long id, @RequestBody TaskEntity taskEntity){
        return taskServices.updatedTask(id,taskEntity);
    }


    // delete
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskServices.deleteTask(id);
        return "Task is delete successfully";
    }


    @Operation(summary = "Get logged-in user's tasks")
    @GetMapping("/my")
    public ResponseEntity<ApiResponseDTO<List<TaskResponseDTO>>> getMyTasks() {

        return ResponseEntity.ok(
                ApiResponseDTO.success(
                        "My tasks fetched",
                        taskServices.getMyTasks()
                )
        );
    }





}
