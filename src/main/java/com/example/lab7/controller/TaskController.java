
package com.example.lab7.controller;

import com.example.lab7.dto.CreateTaskDTO;
import com.example.lab7.dto.TaskDTO;
import com.example.lab7.dto.UpdateTaskDTO;
import com.example.lab7.dto.UpdateTaskUserDTO;
import com.example.lab7.entity.Task;
import com.example.lab7.enumType.TaskStatus;
import com.example.lab7.enumType.TaskType;
import com.example.lab7.mapper.TaskMapper;
import com.example.lab7.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController { //c

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return taskMapper.toDTOs(tasks);
    }

    @GetMapping("/tasks/{taskId}") // e ii
    public TaskDTO getTask(@PathVariable long taskId) {
        Task task = taskService.getTask(taskId);
        return taskMapper.toDTO(task);
    }

    @GetMapping("/tasks/user/{userId}") // e iii
    public List<TaskDTO> getTasksByUser(@PathVariable long userId) {
        List<Task> tasks = taskService.getTasks()
                .stream()
                .filter(task -> task.getUser().getId()==userId)
                .collect(Collectors.toList());

        return taskMapper.toDTOs(tasks);
    }

    @GetMapping("/tasks/user/{userId}/status/{status}") // e iv
    public List<TaskDTO> getTasksByStatusAndUser(@PathVariable String status, @PathVariable long userId) {
        TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
        List<Task> tasks = taskService.getTasks()
                .stream()
                .filter(task -> task.getUser().getId()==userId)
                .filter(task -> task.getStatus()==taskStatus)
                .collect(Collectors.toList());

        return taskMapper.toDTOs(tasks);
    }

    @GetMapping("/tasks/user/{userId}/type/{type}") // e v
    public List<TaskDTO> getTasksByTypeAndUser(@PathVariable String type, @PathVariable long userId) {
        TaskType taskType = TaskType.valueOf(type.toUpperCase());
        List<Task> tasks = taskService.getTasks()
                .stream()
                .filter(task -> task.getUser().getId()==userId)
                .filter(task -> task.getType()==taskType)
                .collect(Collectors.toList());

        return taskMapper.toDTOs(tasks);
    }

    @GetMapping("/tasks/user/{userId}/status/{status}/type/{type}") // e vi
    public List<TaskDTO> getTasksByTypeAndStatusAndUser(@PathVariable String status, @PathVariable String type, @PathVariable long userId) {
        TaskType taskType = TaskType.valueOf(type.toUpperCase());
        TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
        List<Task> tasks = taskService.getTasks()
                .stream()
                .filter(task -> task.getUser().getId()==userId)
                .filter(task -> task.getType()==taskType)
                .filter(task -> task.getStatus()==taskStatus)
                .collect(Collectors.toList());

        return taskMapper.toDTOs(tasks);
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody CreateTaskDTO taskDTO) {
        Task task = taskMapper.fromDTO(taskDTO);
        taskService.createTask(task);
    }

    @PutMapping("/tasks/{taskId}") // e. ix
    public void updateTask(@RequestBody UpdateTaskDTO taskDTO, @PathVariable long taskId) {
        Task taskFromDb = taskService.getTask(taskId);
        if (taskFromDb != null) {
            Task task = taskMapper.fromDTO(taskDTO);
            task.setId(taskId);
            task.setUser(taskFromDb.getUser());
            taskService.updateTask(task);
        }
    }

    @PutMapping("/tasks/{taskId}/user") //e.vii
    public void updateTaskByUser(@RequestBody UpdateTaskUserDTO taskDTO, @PathVariable long taskId) {
        Task taskFromDb = taskService.getTask(taskId);
        if(taskFromDb != null) {
            Task task = taskMapper.fromDTO(taskDTO);
            task.setId(taskId);
            task.setTitle(taskFromDb.getTitle());
            task.setCreateDate(taskFromDb.getCreateDate());
            task.setStatus(taskFromDb.getStatus());
            task.setType(taskFromDb.getType());
            taskService.updateTask(task);
        }
    }

    @DeleteMapping("/tasks/{taskId}") // e viii
    public void deleteTask(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
    }

}
