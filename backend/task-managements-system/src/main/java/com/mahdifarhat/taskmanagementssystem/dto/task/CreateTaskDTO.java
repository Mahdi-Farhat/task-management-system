package com.mahdifarhat.taskmanagementssystem.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskDTO(
        @NotBlank(message = "Title is mandatory")
        @Size(min = 3, max = 100, message = "Title should be between 3 and 100 characters.")
        String title,
        @Size(min = 3, max = 100, message = "Description should be between 3 and 100 characters.")
        String description,
        Boolean status
) {
    
}
