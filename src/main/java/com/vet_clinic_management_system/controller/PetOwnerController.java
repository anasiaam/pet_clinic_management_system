package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.PetOwnerDTO;
import com.vet_clinic_management_system.service.PetOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet_controllers")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    public PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    public void save(@RequestBody PetOwnerDTO petOwnerDTO) {
        petOwnerService.save(petOwnerDTO);
    }

    @PutMapping
    public void update(@RequestBody PetOwnerDTO petOwnerDTO) {
        petOwnerService.update(petOwnerDTO);
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerDTO>> findAll() {
        return ResponseEntity.ok(petOwnerService.findAll());
    }

    @GetMapping("/id/{id}")
    public PetOwnerDTO findById(@PathVariable("id") Integer id) {
        return petOwnerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        petOwnerService.delete(id);
    }
}
