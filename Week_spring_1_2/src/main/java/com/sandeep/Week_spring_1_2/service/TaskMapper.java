package com.sandeep.Week_spring_1_2.service;


import com.sandeep.Week_spring_1_2.dto.TaskRequestDTO;
import com.sandeep.Week_spring_1_2.dto.TaskResponseDTO;
import com.sandeep.Week_spring_1_2.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity toEntity(TaskRequestDTO taskRequestDTO){
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(taskRequestDTO.getTitle());
        taskEntity.setDescription(taskRequestDTO.getDescription());
        taskEntity.setStatus(false);
        return taskEntity;
    }

    public TaskResponseDTO toDto(TaskEntity taskEntity){
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(taskEntity.getId());
        taskResponseDTO.setTitle(taskEntity.getTitle());
        taskResponseDTO.setDescription(taskEntity.getDescription());
        taskResponseDTO.setCompleted(taskEntity.getStatus());
        return taskResponseDTO;
    }



}
