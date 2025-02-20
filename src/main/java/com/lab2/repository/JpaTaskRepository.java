package com.lab2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lab2.entity.Task;

public interface JpaTaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByProjectId(int projectId);

    void deleteByIdAndProjectId(int id, int projectId);

    @Modifying
    @Query(nativeQuery = true, name = "Task.deleteAllByProjectId")
    void deleteAllByProjectId(@Param("projectId") int projectId);

    Optional<Task> findByIdAndProjectId(int id, int projectId);

    @Query(nativeQuery = true, name = "Task.deleteCompleted")
    int deleteCompleted(@Param("projectId") int projectId);
}