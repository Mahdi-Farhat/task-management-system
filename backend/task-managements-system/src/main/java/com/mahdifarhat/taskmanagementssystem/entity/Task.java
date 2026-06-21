package com.mahdifarhat.taskmanagementssystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory.")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
    @Column(nullable = false)
    private String title;

    @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters.")
    @Column()
    private String description;

    @Column(nullable = false)
    private Boolean status = false;

    private String priority;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime due_date;

    private Long project_id;
    private Long assigned_user_id;

    @PrePersist
    protected void onCreate() {
        this.due_date = OffsetDateTime.now();
    }
}
