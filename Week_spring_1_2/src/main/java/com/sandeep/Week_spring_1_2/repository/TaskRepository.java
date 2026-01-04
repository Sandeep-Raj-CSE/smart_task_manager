package com.sandeep.Week_spring_1_2.repository;

import com.sandeep.Week_spring_1_2.entity.TaskEntity;
import com.sandeep.Week_spring_1_2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findByUser(UserEntity user);

    List<TaskEntity> findByUserAndDeletedFalse(UserEntity user);

    List<TaskEntity> findByDeletedFalse();


}
