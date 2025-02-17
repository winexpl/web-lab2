package com.lab2.repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lab2.entity.Project;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcProjectRepository implements ProjectRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Project> projectMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getInt("p_id"));
        project.setName(rs.getString("p_name"));
        project.setDescr(rs.getString("p_descr"));
        var beginDate = rs.getDate("p_begin_date");
        project.setBeginDate(beginDate != null ? beginDate.toLocalDate() : null);
        var endDate = rs.getDate("p_begin_date");
        project.setEndDate(endDate != null ? endDate.toLocalDate() : null);
        return project;
    };

    @Override
    public Project save(Project project) {
        String sqlRequest = "INSERT INTO project (p_name, p_descr, p_begin_date, p_end_date) VALUES (?, ?, ?, ?) RETURNING p_id";
        int id = jdbcTemplate.queryForObject(sqlRequest, Integer.class,
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate()).intValue();
        project.setId(id);
        
        return project;
    }

    @Override
    public int update(Project project) {
        String sqlRequest = "UPDATE project " + //
                        "SET p_name=?, p_descr=?, p_begin_date=?, p_end_date=? " + //
                        "WHERE p_id=?; ";
        return jdbcTemplate.update(sqlRequest,
            project.getName(),
            project.getDescr(),
            project.getBeginDate(),
            project.getEndDate(),
            project.getId());
    }

    @Override
    public int deleteById(int id) {
        String sqlRequest = "DELETE FROM project WHERE p_id=:id";
        return namedParameterJdbcTemplate.update(sqlRequest, Collections.singletonMap("id", id));
    }

    @Override
    public Project findById(int id) {
        String sqlRequest = "SELECT * FROM project WHERE p_id=?";
        try {
            return jdbcTemplate.queryForObject(sqlRequest, projectMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Project> findByRangeOfDates(LocalDate start_date, LocalDate end_date) {
        String sqlRequest = "SELECT * FROM project WHERE " +
            "(p_begin_date BETWEEN :start_date AND :end_date) " +
            "AND (p_end_date BETWEEN :start_date AND :end_date)";
        SqlParameterSource map = new MapSqlParameterSource()
            .addValue("start_date", start_date)
            .addValue("end_date", end_date);

        List<Project> project = namedParameterJdbcTemplate.query(sqlRequest, map, projectMapper);
        return project;
    }

    @Override
    public List<Project> findAll() {
        String sqlRequest = "SELECT * FROM project";
        return namedParameterJdbcTemplate.query(sqlRequest, projectMapper);
    }
    
}
