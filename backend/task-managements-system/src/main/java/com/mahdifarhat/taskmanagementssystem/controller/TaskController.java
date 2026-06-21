package com.mahdifarhat.taskmanagementssystem.controller;

import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/task")    //Base URL for all endpoints, localhost:8080/api/v1/tasks/...
public class TaskController {

    private final TaskService taskService;
    //Constructor Injection
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService= taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody CreateTaskDTO task) {
        TaskResponseDTO savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id,@Valid @RequestBody UpdateTaskDTO updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed/{status}")
    public List<TaskResponseDTO> getTasksByCompletionStatus(@PathVariable boolean status) {
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/search")
    public List<TaskResponseDTO> getTasksByTitle(@RequestParam String title) {
        return taskService.getTasksByTitle(title);
    }

    @GetMapping("/completed/second/{status}")
    public List<TaskResponseDTO> getTasksByStatus(@PathVariable boolean status) {
        return taskService.getTasksByStatus(status);
    }


}
