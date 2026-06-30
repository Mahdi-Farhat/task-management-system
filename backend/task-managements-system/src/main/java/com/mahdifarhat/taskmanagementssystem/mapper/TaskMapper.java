package com.mahdifarhat.taskmanagementssystem.mapper;

import com.mahdifarhat.taskmanagementssystem.dto.role.RoleResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.CreateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.TaskResponseDTO;
import com.mahdifarhat.taskmanagementssystem.dto.task.UpdateTaskDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Role;
import com.mahdifarhat.taskmanagementssystem.entity.Task;
import com.mahdifarhat.taskmanagementssystem.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final RoleService roleService;
    public TaskMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public Task toEntity(CreateTaskDTO dto){
        Role role = null;
        if(dto.roleId() != null){
            role = roleService.getById(dto.roleId());
        }
        return Task.builder()
                .title(dto.title())
                .status(dto.status())
                .description(dto.description())
                .role(role)
                .build();
    }

    public Task toEntity(UpdateTaskDTO dto){
        Role role = null;
        if(dto.roleId() != null){
            role = roleService.getById(dto.roleId());
        }
        return Task.builder()
                .title(dto.title())
                .status(dto.status())
                .description(dto.description())
                .role(role)
                .build();
    }

    public void updateEntityFromReUpdateTaskDTO(Task task, UpdateTaskDTO dto){
        Role role = null;
        if(dto.roleId() != null){
            role = roleService.getById(dto.roleId());
        }
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setRole(role);
    }

    public TaskResponseDTO toTaskResponseDTO(Task task){
        RoleResponseDTO role = null;
        if(task.getRole() != null){
            role = RoleResponseDTO.builder()
                    .roleId(task.getRole().getId())
                    .name(task.getRole().getRoleName())
                    .description(task.getRole().getRoleDescription())
                    .build();
        }

        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .due_date(task.getDue_date())
                .roleResponse(role)
                .build();
    }
}
