package com.chernyak.fapi.models;

import com.chernyak.fapi.models.enums.TaskPriority;
import com.chernyak.fapi.models.enums.TaskStatus;

public class Task extends BaseEntity {
    private String projectId;
    private String priority;
    private String taskStatus;
    private String dueDate;
    private String estimation;
    private String reporter;
    private String assignee;
    private String description;
    private String title;
    private String ticketCode;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporterId) {
        this.reporter = reporterId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assigneeId) {
        this.assignee = assigneeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
