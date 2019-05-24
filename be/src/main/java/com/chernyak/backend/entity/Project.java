package com.chernyak.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "projects")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project extends BaseEntity {

    @Size(min = 3, max = 5, message = "Project code size must be between 3 and 5 symbols")
    @NotBlank(message = "Project code is required")
    @Column(name = "project_code", nullable = false, unique = true)
    private String projectCode;

    @Size(min = 3, max = 20, message = "Project name size must be between 3 and 20 symbols")
    @NotBlank(message = "Project name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private User manager;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
