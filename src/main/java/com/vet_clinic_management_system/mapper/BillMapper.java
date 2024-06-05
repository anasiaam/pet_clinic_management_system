package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.BillDTO;
import com.vet_clinic_management_system.entity.BillEntity;
import com.vet_clinic_management_system.entity.PetEntity;

import java.time.LocalDate;

public class BillMapper {
    private BillMapper() {
        // konstruktor privat
    }

    public static BillEntity toEntity(BillDTO billDTO, PetEntity petEntity) {
        BillEntity billEntity = new BillEntity();
        billEntity.setBillingDate(LocalDate.now());
        billEntity.setAmount(billDTO.getAmount());
        billEntity.setPet(petEntity);
        return billEntity;
    }

    public static BillEntity toEntityForUpdate(BillEntity billEntity, BillDTO billDTO) {
        billEntity.setBillingDate(billDTO.getBillingDate());
        billEntity.setAmount(billDTO.getAmount());
        return billEntity;
    }

    public static BillDTO toDTO(BillEntity billEntity) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(billEntity.getId());
        billDTO.setBillingDate(billEntity.getBillingDate());
        billDTO.setAmount(billEntity.getAmount());
        billDTO.setPet(PetMapper.toDTO(billEntity.getPet()));
        return billDTO;
    }
}
