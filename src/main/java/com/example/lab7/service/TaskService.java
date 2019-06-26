/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lab7.service;

import com.example.lab7.entity.Task;
import com.example.lab7.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Darek
 */
@Service
public class TaskService { //c

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Task getTask(long id) {
        return taskRepository.getOne(id);
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
