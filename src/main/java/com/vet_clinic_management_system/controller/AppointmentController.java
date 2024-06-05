package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.AppointmentDTO;
import com.vet_clinic_management_system.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public void save(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.save(appointmentDTO);
    }

    @PutMapping
    public void update(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.update(appointmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/id/{id}")
    public AppointmentDTO findById(@PathVariable("id") Integer id) {
        return appointmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        appointmentService.delete(id);
    }
}
