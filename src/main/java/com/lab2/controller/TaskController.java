package com.lab2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab2.dto.TaskDTO;
import com.lab2.service.TaskService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/projects/{projectId}/tasks")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> findAll(@RequestParam int projectId) {
        return taskService.findAllTasks(projectId);
    }
}
