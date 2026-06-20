package com.mahdifarhat.taskmanagementssystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private Long owner_id;
    private OffsetDateTime created_at;
}
