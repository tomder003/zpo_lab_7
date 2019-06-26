
package com.example.lab7.mapper;

import com.example.lab7.dto.CreateTaskDTO;
import com.example.lab7.dto.TaskDTO;
import com.example.lab7.dto.UpdateTaskDTO;
import com.example.lab7.dto.UpdateTaskUserDTO;
import com.example.lab7.entity.Task;
import com.example.lab7.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper { //c

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getCreateDate(), task.getStatus(),
                task.getType(), userMapper.toDTO(task.getUser()));
    }

    public List<TaskDTO> toDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(task -> toDTO(task))
                .collect(Collectors.toList());
    }

    public Task fromDTO(CreateTaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setCreateDate(taskDTO.getCreateDate());
        task.setType(taskDTO.getType());
        task.setStatus(taskDTO.getStatus());
        task.setUser(userService.getUser(taskDTO.getUserId()));
        return task;
    }

    public Task fromDTO(UpdateTaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setCreateDate(taskDTO.getCreateDate());
        task.setType(taskDTO.getType());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

    public Task fromDTO(UpdateTaskUserDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setUser(taskDTO.getUser());
        return task;
    }
}
