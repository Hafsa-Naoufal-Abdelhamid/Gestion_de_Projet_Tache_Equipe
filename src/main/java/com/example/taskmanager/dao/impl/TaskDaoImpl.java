package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public void createTask(Task task) {
        String query = "INSERT INTO tasks (title, description, priority, status, creation_date, due_date, project_id, assigned_member_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getPriority().name());  
            statement.setString(4, task.getStatus().name());   
            statement.setDate(5, Date.valueOf(task.getCreationDate()));
            statement.setDate(6, Date.valueOf(task.getDueDate()));
            statement.setLong(7, task.getProjectId());

            if (task.getAssignedMemberId() != null) {
                statement.setLong(8, task.getAssignedMemberId()); 
            } else {
                statement.setNull(8, Types.BIGINT);
            }

            statement.executeUpdate();

            // Retrieve generated ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

    @Override
    public void updateTask(Task task) {
    	 String query = "UPDATE tasks SET title = ?, description = ?, priority = ?, status = ?, due_date = ?, project_id = ?, assigned_member_id = ? WHERE id = ?";
         
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             
             statement.setString(1, task.getTitle());
             statement.setString(2, task.getDescription());
             statement.setString(3, task.getPriority().name());
             statement.setString(4, task.getStatus().name());
             statement.setDate(5, Date.valueOf(task.getDueDate()));
             statement.setLong(6, task.getProjectId());
             
             if (task.getAssignedMemberId() != null) {
                 statement.setLong(7, task.getAssignedMemberId());
             } else {
                 statement.setNull(7, Types.BIGINT);
             }
             
             statement.setInt(8, task.getId());
             
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 0) {
                 throw new RuntimeException("Task not found with id: " + task.getId());
             }
         } catch (SQLException e) {
             throw new RuntimeException("Error while updating the task", e);
         }
    }

    @Override
    public void deleteTask(int taskId) {
    	String query = "DELETE FROM tasks WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, taskId);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Task not found with id: " + taskId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the task", e);
        }
    }

    @Override
    public Task findTaskById(int taskId) {
        Task task = null;
        String query = "SELECT * FROM tasks WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                task = mapResultSetToTask(resultSet);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setProjectId(resultSet.getInt("project_id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void assignTaskToMember(int taskId, int memberId) {
    	 String query = "UPDATE tasks SET assigned_member_id = ? WHERE id = ?";
         
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             
             statement.setInt(1, memberId);
             statement.setInt(2, taskId);
             
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 0) {
                 throw new RuntimeException("Task not found with id: " + taskId);
             }
         } catch (SQLException e) {
             throw new RuntimeException("Error while assigning task to member", e);
         }
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
    	 String query = "UPDATE tasks SET status = ? WHERE id = ?";
         
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             
             statement.setString(1, status);
             statement.setInt(2, taskId);
             
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 0) {
                 throw new RuntimeException("Task not found with id: " + taskId);
             }
         } catch (SQLException e) {
             throw new RuntimeException("Error while updating task status", e);
         }
    }
    
    public Task mapResultSetToTask(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title")); 
        task.setDescription(resultSet.getString("description"));
        task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
        task.setProjectId(resultSet.getInt("project_id"));
        return task;
    }
    
    
}
