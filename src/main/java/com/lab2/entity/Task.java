package com.lab2.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import lombok.Data;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "Task.deleteCompleted",
    query = "DELETE FROM task WHERE t_p_id=:pId AND t_is_completed=true")
})

@Data
public class Task {
    @Id
    private int id;
    private int PId;
    private String name;
    private boolean completed;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "t_p_key")
    private Project project;


}