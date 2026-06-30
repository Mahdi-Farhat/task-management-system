package com.mahdifarhat.taskmanagementssystem.dto.role;

import lombok.Builder;

@Builder
public record RoleResponseDTO(
        Long roleId,
        String name,
        String description

) {
}
