package com.mahdifarhat.taskmanagementssystem.service;

import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id).map(
                task -> {
                    task.setDescription(updatedTask.getDescription());
                    task.setPriority(updatedTask.getPriority());
                    task.setTitle(updatedTask.getTitle());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(
                task -> {
                    taskRepository.delete(task);
                    return true;
                }).orElse(false);
    }

    public List<Task> getTasksByCompletionStatus(boolean status) {
        return taskRepository.findByStatus(true);
    }

    public List<Task> getTasksByTitle(String title) {
        return taskRepository.findByTitleIgnoreCaseContaining(title);
    }

    public List<Task> getTasksByStatus(boolean status) {
        return taskRepository.findTasksByCompletionStatus(status);
    }




}
