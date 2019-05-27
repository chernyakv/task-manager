package com.chernyak.fapi.validators;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.models.User;
import com.chernyak.fapi.service.ProjectService;
import com.chernyak.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProjectValidator implements Validator {

    private final ProjectService projectService;

    @Autowired
    public ProjectValidator(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Project project = (Project) o;
        if (project.getCode().length() < 5) {
            errors.rejectValue("code", "inv_code length should be > 5");
        }
        Project project1 = projectService.getProjectByCode(project.getCode());
        if (!(project1 == null)) {
            errors.rejectValue("code", "inv_" + project.getCode() + " already exists");
        }
        if (project.getSummary().length() < 5) {
            errors.rejectValue("summary", "inv_summary length should be > 5");
        }
    }
}
