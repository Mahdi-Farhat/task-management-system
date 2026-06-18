package com.mahdifarhat.taskmanagementssystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Boolean status;
    private String priority;
    private OffsetDateTime due_date;
    private Long project_id;
    private Long assigned_user_id;
}
