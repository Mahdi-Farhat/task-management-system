package com.mahdifarhat.taskmanagementssystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private String content;
    private Long task_id;
    private Long user_id;
    private OffsetDateTime created_at;
}
