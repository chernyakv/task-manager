package com.chernyak.backend.entity;

import com.chernyak.backend.entity.enums.TaskPriority;
import com.chernyak.backend.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tasks")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project projectId;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "estimation")
    private Date estimation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assigneeId;

    @Column(name = "description")
    private String description;

    @Column(name = "ticket_code")
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getEstimation() {
        return estimation;
    }

    public void setEstimation(Date estimation) {
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
}
