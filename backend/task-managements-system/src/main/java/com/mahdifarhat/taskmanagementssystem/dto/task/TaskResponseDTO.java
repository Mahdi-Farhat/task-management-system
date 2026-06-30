package com.mahdifarhat.taskmanagementssystem.dto.task;

import com.mahdifarhat.taskmanagementssystem.dto.role.RoleResponseDTO;
import com.mahdifarhat.taskmanagementssystem.entity.Role;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Boolean status,
        OffsetDateTime due_date,
        RoleResponseDTO roleResponse
) {

}
