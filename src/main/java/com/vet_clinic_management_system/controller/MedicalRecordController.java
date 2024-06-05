package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.MedicalRecordDTO;
import com.vet_clinic_management_system.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical_records")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public void save(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.save(medicalRecordDTO);
    }

    @PutMapping
    public void update(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.update(medicalRecordDTO);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> findAll() {
        return ResponseEntity.ok(medicalRecordService.findAll());
    }

    @GetMapping("/id/{id}")
    public MedicalRecordDTO findById(@PathVariable("id") Integer id) {
        return medicalRecordService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        medicalRecordService.delete(id);
    }
}
