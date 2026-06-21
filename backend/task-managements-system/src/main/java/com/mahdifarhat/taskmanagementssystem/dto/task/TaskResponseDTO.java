package com.mahdifarhat.taskmanagementssystem.dto.task;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Boolean status,
        OffsetDateTime due_date
) {

}
