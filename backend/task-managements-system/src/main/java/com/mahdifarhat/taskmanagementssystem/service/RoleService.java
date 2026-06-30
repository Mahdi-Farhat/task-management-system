package com.mahdifarhat.taskmanagementssystem.service;

import com.mahdifarhat.taskmanagementssystem.entity.Role;
import com.mahdifarhat.taskmanagementssystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }
}
