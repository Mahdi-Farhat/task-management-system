package com.mahdifarhat.taskmanagementssystem.service;

import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.exception.TaskNotFoundException;
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

    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask){
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
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
