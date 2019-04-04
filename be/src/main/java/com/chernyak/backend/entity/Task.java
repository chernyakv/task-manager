package com.chernyak.backend.entity;

import com.chernyak.backend.entity.enums.TaskPriority;
import com.chernyak.backend.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TASKS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
    private Project projectId;

    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "ESTIMATION")
    private Date estimation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTER_ID")
    private User reporterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNEE_ID")
    private User assigneeId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TICKET_CODE")
    private String ticketCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public User getReporterId() {
        return reporterId;
    }

    public void setReporterId(User reporterId) {
        this.reporterId = reporterId;
    }

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
