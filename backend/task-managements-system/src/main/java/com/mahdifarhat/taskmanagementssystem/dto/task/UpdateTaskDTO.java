package com.mahdifarhat.taskmanagementssystem.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTaskDTO(
        @NotBlank
        @Size(min =3, max =100, message ="Title is mandatory")
        String title,
        @Size(min =3, max = 500, message = "Description is mandatory")
        String description,
        Boolean status,
        Long roleId
) {
}
