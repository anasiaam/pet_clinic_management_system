package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public void save(@RequestBody PetDTO petDTO) {
        petService.save(petDTO);
    }

    @PutMapping
    public void update(@RequestBody PetDTO petDTO) {
        petService.update(petDTO);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> findAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/id/{id}")
    public PetDTO findById(@PathVariable("id") Integer id) {
        return petService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        petService.delete(id);
    }
}
