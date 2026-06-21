package com.mahdifarhat.taskmanagementssystem.mapper;

import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(CreateTaskDTO dto){
        return Task.builder()
                .title(dto.title())
                .status(dto.status())
                .description(dto.description())
                .build();
    }

    public Task toEntity(UpdateTaskDTO dto){
        return Task.builder()
                .title(dto.title())
                .status(dto.status())
                .description(dto.description())
                .build();
    }

    public void updateEntityFromReUpdateTaskDTO(Task task, UpdateTaskDTO dto){
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
    }

    public TaskResponseDTO toTaskResponseDTO(Task task){
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .due_date(task.getDue_date())
                .build();
    }
}
