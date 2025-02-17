package com.lab2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lab2.entity.Project;
import com.lab2.entity.Task;

@Primary
public interface JpaTaskRepository extends JpaRepository<Task, Integer>, TaskRepository {
    List<Task> findByPId(int pId);
    List<Task> findByPIdAndId(int pId, int id);
    Task save(Task task);
    int deleteByPIdAndId(int pId, int id);
    
    @Query(nativeQuery = true, name = "Task.deleteCompleted")
    int deleteCompleted(@Param("pId") int pId);
}