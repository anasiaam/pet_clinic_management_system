package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.TreatmentDTO;
import com.vet_clinic_management_system.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping
    public void save(@RequestBody TreatmentDTO treatmentDTO) {
        treatmentService.save(treatmentDTO);
    }

    @PutMapping
    public void update(@RequestBody TreatmentDTO treatmentDTO) {
        treatmentService.update(treatmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentDTO>> findAll() {
        return ResponseEntity.ok(treatmentService.findAll());
    }

    @GetMapping("/id/{id}")
    public TreatmentDTO findById(@PathVariable("id") Integer id) {
        return treatmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        treatmentService.delete(id);
    }
}
