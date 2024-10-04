package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void createTask(Task task) {
        // Empty implementation
    }

    @Override
    public void updateTask(Task task) {
        // Empty implementation
    }

    @Override
    public void deleteTask(int taskId) {
        // Empty implementation
    }

    @Override
    public Task findTaskById(int taskId) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId, int page, int pageSize) {
        // Empty implementation
        return null;
    }

    @Override
    public void assignTaskToMember(int taskId, int memberId) {
        // Empty implementation
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
        // Empty implementation
    }
}
