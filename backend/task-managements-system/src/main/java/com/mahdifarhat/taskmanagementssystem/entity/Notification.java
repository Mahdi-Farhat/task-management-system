package com.mahdifarhat.taskmanagementssystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long id;
    private String message;
    private Long user_id;
    private Boolean is_read;
    private OffsetDateTime created_at;

}
