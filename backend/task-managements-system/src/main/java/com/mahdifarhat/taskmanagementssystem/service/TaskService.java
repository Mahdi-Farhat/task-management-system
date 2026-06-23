package com.mahdifarhat.taskmanagementssystem.service;

import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.exception.TaskNotFoundException;
import com.mahdifarhat.taskmanagementssystem.mapper.TaskMapper;
import com.mahdifarhat.taskmanagementssystem.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        Page<Task> pages = taskRepository.findAll(pageable);
        return pages.map(taskMapper::toTaskResponseDTO);
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
        final List<Task> completedTasks = taskRepository.findByStatus(status);
        return completedTasks.stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public Page<TaskResponseDTO> getTasksByCompletionStatus(boolean status, Pageable pageable) {
        final Page<Task> completedTasks = taskRepository.findByStatus(status, pageable);
        return completedTasks.map(taskMapper::toTaskResponseDTO);
    }

    public List<TaskResponseDTO> getTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    public Page<TaskResponseDTO> getTasksByTitle(String title, Pageable pageable) {
        final Page<Task> tasksByTitle = taskRepository.findByTitleContainingIgnoreCase(title, pageable);
        return tasksByTitle.map(taskMapper::toTaskResponseDTO);
    }

    public List<TaskResponseDTO> getTasksByStatus(boolean status) {
        return taskRepository.findTasksByCompletionStatus(status)
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }




}
