package com.mahdifarhat.taskmanagementssystem.repository;

import com.mahdifarhat.taskmanagementssystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Method name Queries
    List<Task> findByStatus(boolean completed);

    List<Task> findByTitleIgnoreCaseContaining(String title);

    //JPQL Query
    @Query("Select t from Task t where t.status = :completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") boolean completed);

}
