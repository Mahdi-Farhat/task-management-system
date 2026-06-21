package com.mahdifarhat.taskmanagementssystem.controller;

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
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByCompletionStatus(@PathVariable boolean status) {
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/search")
    public List<Task> getTasksByTitle(@RequestParam String title) {
        return taskService.getTasksByTitle(title);
    }

    @GetMapping("/completed/second/{status}")
    public List<Task> getTasksByStatus(@PathVariable boolean status) {
        return taskService.getTasksByStatus(status);
    }


}
