package com.lab2.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "Project.update",
    query = "UPDATE project SET p_name=:name, p_descr=:descr, p_begin_date=:beginDate, p_end_date=:endDate " + //
                        "WHERE p_id=:id; "),
    @NamedNativeQuery(name = "Project.findByRangeOfDates",
    query = "SELECT * FROM project WHERE " +
            "(p_begin_date BETWEEN :beginDate AND :endDate) " +
            "AND (p_end_date BETWEEN :beginDate AND :endDate)")
})
@Data
public class Project {
    @Id
    private int id;
    private String name;
    private String descr;
    private LocalDate beginDate;
    private LocalDate endDate;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}