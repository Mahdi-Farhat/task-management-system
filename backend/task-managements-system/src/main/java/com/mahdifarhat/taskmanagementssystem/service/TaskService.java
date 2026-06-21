package com.mahdifarhat.taskmanagementssystem.service;

import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.exception.TaskNotFoundException;
import com.mahdifarhat.taskmanagementssystem.mapper.TaskMapper;
import com.mahdifarhat.taskmanagementssystem.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponseDTO> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id){
        Task entityTask = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        return taskMapper.toTaskResponseDTO(entityTask);
    }

    public TaskResponseDTO createTask(CreateTaskDTO task){
        Task entityTask = taskMapper.toEntity(task);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toTaskResponseDTO(savedTask);
    }

    public TaskResponseDTO updateTask(Long id, UpdateTaskDTO updatedTask){
        Task entityTask = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        taskMapper.updateEntityFromReUpdateTaskDTO(entityTask, updatedTask);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toTaskResponseDTO(savedTask);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    public List<TaskResponseDTO> getTasksByCompletionStatus(boolean status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByTitle(String title) {
        return taskRepository.findByTitleIgnoreCaseContaining(title)
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByStatus(boolean status) {
        return taskRepository.findTasksByCompletionStatus(status)
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }




}
