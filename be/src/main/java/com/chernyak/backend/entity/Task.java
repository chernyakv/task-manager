package com.chernyak.backend.entity;

import com.chernyak.backend.entity.enums.TaskPriority;
import com.chernyak.backend.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project projectId;

    @NotNull(message = "Task priority is required")
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @NotNull(message = "Task status is required")
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "due_date")
    private Long dueDate;

    @Column(name = "estimation")
    private Long estimation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assigneeId;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "ticket_code", nullable = false)
    private String ticketCode;

    @JsonIgnore
    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus status) {
        this.taskStatus = status;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Long getEstimation() {
        return estimation;
    }

    public void setEstimation(Long estimation) {
        this.estimation = estimation;
    }

    @JsonIgnore
    public User getReporterId() {
        return reporterId;
    }

    public void setReporterId(User reporterId) {
        this.reporterId = reporterId;
    }
    @JsonIgnore
    public User getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(User assigneeId) {
        this.assigneeId = assigneeId;
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
