package com.chernyak.fapi.validators;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.models.Task;
import com.chernyak.fapi.service.ProjectService;
import com.chernyak.fapi.service.TaskService;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskValidator implements Validator {
    List<String> priorityList = Arrays.asList("BLOCKER", "CRITICAL", "MAJOR", "NORMAL", "MINOR");
    List<String> statusList = Arrays.asList("OPEN", "IN_PROGRESS",  "READY_FOR_TEST", "REOPEN", "CLOSED");

    private final TaskService taskService;

    @Autowired
    public TaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Task task = (Task) o;
        if (task.getTitle().length() < 10) {
            errors.rejectValue("title", "inv_task name must be more than 10 characters");
        }
        if (task.getDescription().length() < 10) {
            errors.rejectValue("description", "inv_task description must be more than 10 characters");
        }
        if(Long.parseLong(task.getDueDate())<System.currentTimeMillis()){
            errors.rejectValue("dueDate", "inv_due date has passed");
        }
    }
}
