package com.mahdifarhat.taskmanagementssystem.controller;

import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.service.TaskService;
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
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
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
