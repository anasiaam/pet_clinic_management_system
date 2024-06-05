package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.BillDTO;

import java.util.List;

public interface BillService {
    void save(BillDTO billDTO);
    BillDTO findById(Integer id);
    void update(BillDTO billDTO);
    List<BillDTO> findAll();
    void delete(Integer id);
}
