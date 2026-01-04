package com.sandeep.Week_spring_1_2.dto;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j

public class TaskRequestDTO {

    @NotBlank
    private String title;

    @Size(max = 200)
    private String description;



}
