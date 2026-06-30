package com.mahdifarhat.taskmanagementssystem.repository;

import com.mahdifarhat.taskmanagementssystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
