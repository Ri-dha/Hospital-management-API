package com.azu.hospital.all_user_sevices.specialist_service;

public enum HospitalSpecialty {
    DOCTOR("Doctor"),
    NURSE("Nurse"),
    PHARMACIST("Pharmacist"),
    LAB_TECHNICIAN("Lab Technician"),
    RADIOLOGIST("Radiologist"),
    PHYSIOTHERAPIST("Physiotherapist"),
    SURGEON("Surgeon"),
    DENTIST("Dentist"),
    OPTOMETRIST("Optometrist"),
    DIETITIAN("Dietitian"),
    RESPIRATORY_THERAPIST("Respiratory Therapist"),
    OCCUPATIONAL_THERAPIST("Occupational Therapist"),
    MIDWIFE("Midwife"),
    PARAMEDIC("Paramedic"),
    ANESTHESIOLOGIST("Anesthesiologist"),
    CARDIOLOGIST("Cardiologist"),
    GYNECOLOGIST("Gynecologist"),
    UROLOGIST("Urologist"),
    PEDIATRICIAN("Pediatrician"),
    ORTHOPEDIC_SURGEON("Orthopedic Surgeon"),
    NEUROLOGIST("Neurologist"),
    ONCOLOGIST("Oncologist"),
    DERMATOLOGIST("Dermatologist"),
    ENT_SPECIALIST("ENT Specialist"),
    EMERGENCY_PHYSICIAN("Emergency Physician"),
    INFECTIOUS_DISEASE_SPECIALIST("Infectious Disease Specialist"),
    INTENSIVIST("Intensivist"),
    GERIATRICIAN("Geriatrician"),
    ALLERGIST("Allergist"),
    NEONATOLOGIST("Neonatologist"),
    RHEUMATOLOGIST("Rheumatologist"),
    FORENSIC_PATHOLOGIST("Forensic Pathologist"),
    ADMINISTRATIVE_WORKER("Administrative Worker"),
    CLEANING_WORKER("Cleaning Worker"),
    ADMINISTRATIVE_ASSISTANT("Administrative Assistant"),
    HR_MANAGER("HR Manager"),
    FINANCE_MANAGER("Finance Manager"),
    IT_ADMINISTRATOR("IT Administrator"),
    FACILITY_MANAGER("Facility Manager"),
    MEDICAL_RECORDS_CLERK("Medical Records Clerk"),
    BILLING_SPECIALIST("Billing Specialist"),
    APPOINTMENT_COORDINATOR("Appointment Coordinator"),
    SECRETARY("Secretary"),
    ACCOUNTANT("Accountant");

    private final String displayName;

    HospitalSpecialty(String displayName) {
        this.displayName = displayName;
    };

    public String getDisplayName() {
        return displayName;
    };
}

