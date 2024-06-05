package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/id/{id}")
    public UserDTO findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
