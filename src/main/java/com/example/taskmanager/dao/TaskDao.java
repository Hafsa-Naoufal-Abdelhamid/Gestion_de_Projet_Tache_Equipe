package com.example.taskmanager.dao;

import com.example.taskmanager.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    Task findTaskById(int taskId);
    void assignTaskToMember(int taskId, int memberId);
    void updateTaskStatus(int taskId, String status);
    Task mapResultSetToTask(ResultSet resultSet) throws SQLException;    
    List<Task> getTasksByProjectId(int projectId, int page, int pageSize);  
}
