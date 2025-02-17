package com.lab2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab2.dto.TaskDTO;
import com.lab2.entity.Task;
import com.lab2.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> findAllTasks(int pId) {
        return taskRepository.findByPId(pId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setCompleted(task.isCompleted());
        return taskDTO;
    }
}
