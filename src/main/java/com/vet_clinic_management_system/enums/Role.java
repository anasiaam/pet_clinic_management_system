package com.vet_clinic_management_system.enums;

public enum Role {
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    RECEPTIONIST("RECEPTIONIST");
    String value;

    Role(String value) {
        this.value = value;
    }

}
