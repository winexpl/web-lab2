package com.lab2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab2.entity.Project;
import com.lab2.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public int updateProject(Project project) {
        return projectRepository.update(project);
    }

    public int removeProject(int id) {
        return projectRepository.deleteById(id);
    }

    public Project findProjectById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> findProjectByRangeOfDates(LocalDate start_date, LocalDate end_date) {
        return projectRepository.findByRangeOfDates(start_date, end_date);
    }

    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

}
