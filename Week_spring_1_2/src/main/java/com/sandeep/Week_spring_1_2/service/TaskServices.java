package com.sandeep.Week_spring_1_2.service;


import com.sandeep.Week_spring_1_2.dto.TaskRequestDTO;
import com.sandeep.Week_spring_1_2.dto.TaskResponseDTO;
import com.sandeep.Week_spring_1_2.entity.TaskEntity;
import com.sandeep.Week_spring_1_2.entity.UserEntity;
import com.sandeep.Week_spring_1_2.exception.ResourceNotFoundException;
import com.sandeep.Week_spring_1_2.repository.TaskRepository;
import com.sandeep.Week_spring_1_2.repository.UserRepository;
import com.sandeep.Week_spring_1_2.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServices {

    private final List<TaskEntity> tasks = new ArrayList<>();
    private long number = 1;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

//    public TaskResponseDTO createTask(TaskRequestDTO taskEntity){
////        taskEntity.setId(number++);
////        tasks.add(taskEntity);
////        return taskEntity;
//
//       // return taskRepository.save(taskEntity);
//
//        TaskEntity task = taskMapper.toEntity(taskEntity);
//        TaskEntity saved = taskRepository.save(task);
//        return taskMapper.toDto(saved);
//    }

public TaskResponseDTO createTask(TaskRequestDTO dto) {

    String username = SecurityUtil.getCurrentUsername();

    UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    TaskEntity task = taskMapper.toEntity(dto);
    task.setUser(user);

    TaskEntity saved = taskRepository.save(task);
    return taskMapper.toDto(saved);
}



    public List<TaskEntity> getAllTask(){
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Long id){
//        return tasks.stream()
//                .filter(t->t.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        return taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public TaskEntity updatedTask(Long id, TaskEntity updatedTask){
        TaskEntity taskEntity = getTaskById(id);

        if(taskEntity != null){
            taskEntity.setTitle(updatedTask.getTitle());
            taskEntity.setDescription(updatedTask.getDescription());
            taskEntity.setStatus(updatedTask.getStatus());
           return taskRepository.save(taskEntity);

        }
        return null;
    }


    public void deleteTask(Long id){
//        tasks.removeIf(t->t.getId().equals(id));
        TaskEntity task = getTaskById(id);
        task.setDeleted(true);
        taskRepository.save(task);
    }


    public List<TaskResponseDTO> getMyTasks() {

        String username = SecurityUtil.getCurrentUsername();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return taskRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }


    public void restoreTask(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setDeleted(false);
        taskRepository.save(task);
    }



}
