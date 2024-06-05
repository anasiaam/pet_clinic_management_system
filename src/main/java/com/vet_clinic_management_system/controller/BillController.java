package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.BillDTO;
import com.vet_clinic_management_system.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public void save(@RequestBody BillDTO billDTO) {
        billService.save(billDTO);
    }

    @PutMapping
    public void update(@RequestBody BillDTO billDTO) {
        billService.update(billDTO);
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll() {
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping("/id/{id}")
    public BillDTO findById(@PathVariable("id") Integer id) {
        return billService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        billService.delete(id);
    }
}
