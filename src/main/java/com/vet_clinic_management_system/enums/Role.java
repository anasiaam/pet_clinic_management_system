package com.vet_clinic_management_system.enums;

public enum Role {
    ADMIN("admin"),
    DOCTOR("doctor"),
    RECEPTIONIST("receptionist");
    String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
