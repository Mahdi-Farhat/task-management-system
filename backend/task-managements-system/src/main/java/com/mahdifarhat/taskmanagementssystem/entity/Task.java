package com.mahdifarhat.taskmanagementssystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Column(nullable = false)
    private Boolean status = false;

    private String priority;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime due_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    private Long project_id;
    private Long assigned_user_id;

    @PrePersist
    protected void onCreate() {
        this.due_date = OffsetDateTime.now();
    }
}
