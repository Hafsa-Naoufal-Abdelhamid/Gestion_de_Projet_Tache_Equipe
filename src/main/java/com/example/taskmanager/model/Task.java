package com.example.taskmanager.model;

import java.time.LocalDate;

public class Task {
    public static final String Priority = null;
	private int id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private Long projectId;
    private Long assignedMemberId;

  
    public Task() {}

    // Constructor with all fields
    public Task(int id, String title, String description, Priority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate, Long projectId, Long assignedMemberId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.assignedMemberId = assignedMemberId;
    }

    // Getters and Setters for the ID field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters for other fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public Long getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int i) {
        this.projectId = i;
    }

    public Long getAssignedMemberId() {
        return assignedMemberId;
    }
   
    public void setAssignedMemberId(Long assignedMemberId) {
        this.assignedMemberId = assignedMemberId;
    }

}
