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

    //Dependencies

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    //----------------------------------------------------------------------------------------
    //Constructor

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    //----------------------------------------------------------------------------------------
    //CRUD Operations

    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        Page<Task> pages = taskRepository.findAll(pageable);
        return pages.map(taskMapper::toTaskResponseDTO);
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

    //----------------------------------------------------------------------------------------
    //Search & Filtering

    public Page<TaskResponseDTO> getTasksByCompletionStatus(boolean status, Pageable pageable) {
        final Page<Task> completedTasks = taskRepository.findByStatus(status, pageable);
        return completedTasks.map(taskMapper::toTaskResponseDTO);
    }

    public List<TaskResponseDTO> getTasksByCompletionStatus(boolean status) {
        final List<Task> completedTasks = taskRepository.findByStatus(status);
        return mapToTaskResponseDTO(completedTasks);
    }

    public Page<TaskResponseDTO> getTasksByTitle(String title, Pageable pageable) {
        final Page<Task> tasksByTitle = taskRepository.findByTitleContainingIgnoreCase(title, pageable);
        return tasksByTitle.map(taskMapper::toTaskResponseDTO);
    }

    public List<TaskResponseDTO> getTasksByTitle(String title) {
        return mapToTaskResponseDTO(taskRepository.findByTitleContainingIgnoreCase(title));
    }

    public List<TaskResponseDTO> getTasksByStatus(boolean status) {
        return mapToTaskResponseDTO(taskRepository.findTasksByCompletionStatus(status));
    }

    public Page<TaskResponseDTO> searchTasksByTitleAndStatus(String title,Boolean status, Pageable pageable) {
        final Page<Task> tasksByTitleAndStatus = taskRepository.findByTitleAndStatus(title, status, pageable);
        return tasksByTitleAndStatus.map(taskMapper::toTaskResponseDTO);
    }

    //----------------------------------------------------------------------------------------
    //Private Helpers

    private List<TaskResponseDTO> mapToTaskResponseDTO(List<Task> tasks){
        return tasks.stream()
                .map(taskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

//  Dependencies
//--------------------
//  Constructor
//--------------------
//  CRUD Operations
//              getAll
//              getById
//              create
//              update
//              delete
//---------------------
//  Business Operations
//              assignTask
//              changeStatus
//              addComment
//              markNotificationAsRead
//----------------------
//  Search & Filtering
//              findByTitle
//              findByStatus
//              searchByTitleAndStatus
//-----------------------
//    Private Helpers
}
