package com.sandeep.Week_spring_1_2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
