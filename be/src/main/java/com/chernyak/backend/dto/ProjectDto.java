package com.chernyak.backend.dto;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.repository.ProjectRepository;
import com.chernyak.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PublicKey;

public class ProjectDto {

    private Long id;
    private String manager;
    private String code;
    private String summary;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
