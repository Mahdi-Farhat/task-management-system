package com.mahdifarhat.taskmanagementssystem.controller;

import com.mahdifarhat.taskmanagementssystem.entity.Role;
import com.mahdifarhat.taskmanagementssystem.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Role findById(Long id) {
        return roleService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Role create = roleService.create(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
}
