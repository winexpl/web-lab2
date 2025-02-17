package com.lab2.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TaskDTO {
    private String name;
    private LocalDate endDate;
    private boolean completed;
}
