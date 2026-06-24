package com.mahdifarhat.taskmanagementssystem.controller;

import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/task")    //Base URL for all endpoints, localhost:8080/api/v1/tasks/...
public class TaskController {

    //---------------------------------------------------------------------------------------------
    //Constructor Injection
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService= taskService;
    }
    //---------------------------------------------------------------------------------------------
    //CRUD Endpoints

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "title") String sortBy,
                                                           @RequestParam(defaultValue = "DESC")  String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskResponseDTO> taskPage = taskService.getAllTasks(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", taskPage.getContent());
        response.put("currentPage", taskPage.getNumber());
        response.put("totalItems", taskPage.getTotalElements());
        response.put("totalPages", taskPage.getTotalPages());
        response.put("sortBy", sortBy);
        response.put("hasPreviousPage", taskPage.hasPrevious());
        response.put("hasNext", taskPage.hasNext());

        return new ResponseEntity<>(response,HttpStatus.OK);
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

    //---------------------------------------------------------------------------------------------
    //Search & Filtering Endpoints

    @GetMapping("/page/search")
    public ResponseEntity<Map<String, Object>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "DESC")  String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskResponseDTO> taskPage;
        if(title != null && status != null) {
            //Filter by both
            taskPage = taskService.searchTasksByTitleAndStatus(title, status, pageable);
        }
        else if(title != null) {
            //Filter by title only
            taskPage = taskService.getTasksByTitle(title, pageable);
        }
        else  if(status != null) {
            taskPage = taskService.getTasksByCompletionStatus(status, pageable);
        }
        else {
            taskPage = taskService.getAllTasks(pageable);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", taskPage.getContent());
        response.put("currentPage", taskPage.getNumber());
        response.put("totalItems", taskPage.getTotalElements());
        response.put("totalPages", taskPage.getTotalPages());
        response.put("sortBy", sortBy);
        response.put("hasPreviousPage", taskPage.hasPrevious());
        response.put("hasNext", taskPage.hasNext());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public List<TaskResponseDTO> getTasksByCompletionStatus(@PathVariable boolean status) {
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/page/status/{status}")
    public ResponseEntity<Map<String, Object>> getTasksByCompletionStatus(@PathVariable boolean status,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskResponseDTO> tasksCompleted = taskService.getTasksByCompletionStatus(status, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", tasksCompleted.getContent());
        response.put("currentPage", tasksCompleted.getNumber());
        response.put("totalItems", tasksCompleted.getTotalElements());
        response.put("totalPages", tasksCompleted.getTotalPages());
        response.put("sortBy", sortBy);
        response.put("hasPreviousPage", tasksCompleted.hasPrevious());
        response.put("hasNext", tasksCompleted.hasNext());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    @GetMapping("/status/{status}")
//    public List<TaskResponseDTO> getTasksByStatus(@PathVariable boolean status) {
//        return taskService.getTasksByStatus(status);
//    }

    @GetMapping("/search-by-title")
    public List<TaskResponseDTO> getTasksByTitle(@RequestParam String title) {
        return taskService.getTasksByTitle(title);
    }

    @GetMapping("/page/search-by-title")
    public ResponseEntity<Map<String, Object>> getTasksByTitle(@RequestParam String title,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "DESC")  String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskResponseDTO> tasksCompleted = taskService.getTasksByTitle(title, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", tasksCompleted.getContent());
        response.put("currentPage", tasksCompleted.getNumber());
        response.put("totalItems", tasksCompleted.getTotalElements());
        response.put("totalPages", tasksCompleted.getTotalPages());
        response.put("sortBy", sortBy);
        response.put("hasPreviousPage", tasksCompleted.hasPrevious());
        response.put("hasNext", tasksCompleted.hasNext());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //---------------------------------------------------------------------------------------------
    //Business Operations Endpoints

    //---------------------------------------------------------------------------------------------
    //Statistics Endpoints

    //---------------------------------------------------------------------------------------------
    //Private Helpers


// =====================================================
//
// Dependencies
//
// =====================================================
//
// Constructor
//
// =====================================================
//
// CRUD Endpoints
//
// =====================================================
// =====================================================
//
// Search & Filtering Endpoints
//
// =====================================================
// =====================================================
//
// Business Operations Endpoints
//
// =====================================================
// =====================================================
//
// Statistics Endpoints
//
// =====================================================
// =====================================================
//
// Private Helper Methods
// =====================================================

}
