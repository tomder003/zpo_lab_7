
package com.example.lab7.repository;

import com.example.lab7.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> { //c
}
