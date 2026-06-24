package com.mahdifarhat.taskmanagementssystem.repository;

import com.mahdifarhat.taskmanagementssystem.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Method name Queries
    List<Task> findByStatus(boolean status);

    List<Task> findByTitleContainingIgnoreCase(String title);

    //New paginated methods
    Page<Task> findByStatus(boolean status, Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    //JPQL Query
    @Query("Select t from Task t where t.status = :status")
    List<Task> findTasksByCompletionStatus(@Param("status") boolean status);

    @Query("Select t from Task t where t.status = :status")
    Page<Task> findTasksByCompletionStatus(@Param("status") boolean status, Pageable pageable);

    Page<Task> findByTitleAndStatus(String title,Boolean status, Pageable pageable);
}
