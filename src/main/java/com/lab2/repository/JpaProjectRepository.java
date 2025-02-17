package com.lab2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lab2.entity.Project;

@Primary
public interface JpaProjectRepository extends JpaRepository<Project, Integer>, ProjectRepository {
    @Query(nativeQuery = true, name = "Project.update")
    void updateProject(@Param("id") Integer id,
                        @Param("name") String name,
                        @Param("descr") String descr,
                        @Param("beginDate") LocalDate beginDate,
                        @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, name = "Project.update")
    List<Project> findByRangeOfDates(@Param("beginDate") LocalDate beginDate,
                        @Param("endDate") LocalDate endDate);
}
