package com.azu.hospital.utils.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum EnumDoctorRole {
    HOSPITAL_MANAGER("Hospital Manager"),
    HOSPITAL_ASSISTANCE_MANAGER("Hospital Assistance Manager"),
    SURGICAL_HEAD_CHIEF("Surgical Head Chief"),
    INTERNAL_HEAD_CHIEF("Internal Head Chief"),
    ANESTHETIC_HEAD_CHIEF("Anesthetic Head Chief"),
    OPHTHALMOLOGY_HEAD_CHIEF("Ophthalmology Head Chief"),
    PHYSICAL_THERAPY_MANAGER("Physical Therapy Manager"),
    PHYSICAL_THERAPY_MANAGER_ASSIST("Physical Therapy Manager Assist"),
    DEPARTMENT_MANAGER("Department Manager"),
    DEPARTMENT_MANAGER_ASSISTANT("Department Manager Assistant"),
    WARD_MANAGER_ASSISTANT("Ward Manager Assistant"),
    WARD_MANAGER("Ward Manager"),
    DOCTOR("Doctor"),
    ANESTHESIOLOGIST("Anesthesiologist"),
    SURGEON("Surgeon"),
    PEDIATRICIAN("Pediatrician"),
    PHARMACISTS("Pharmacists"),
    ALLERGIST_IMMUNOLOGIST("Allergist/Immunologist"),
    CARDIOLOGIST("Cardiologist"),
    DERMATOLOGIST("Dermatologist"),
    ENDOCRINOLOGIST("Endocrinologist"),
    FAMILY_PHYSICIAN("Family Physician"),
    GASTROENTEROLOGIST("Gastroenterologist"),
    GENETICIST("Geneticist"),
    HEMATOLOGIST("Hematologist"),
    HOSPICE_AND_PALLIATIVE_MEDICINE_SPECIALIST("Hospice and Palliative Medicine Specialist"),
    INFECTIOUS_DISEASE_PHYSICIAN("Infectious Disease Physician"),
    INTERNAL_MEDICINE("Internal Medicine"),
    NEPHROLOGIST("Nephrologist"),
    NEUROLOGIST("Neurologist"),
    OBSTETRICIAN_GYNECOLOGIST("Obstetrician/Gynecologist"),
    ONCOLOGIST("Oncologist"),
    OPHTHALMOLOGIST("Ophthalmologist"),
    ORTHOPEDIST("Orthopedist"),
    OTOLARYNGOLOGIST("Otolaryngologist"),
    OSTEOPATH("Osteopath"),
    PATHOLOGIST("Pathologist"),
    PHYSICIAN_EXECUTIVE("Physician Executive"),
    PLASTIC_SURGEON("Plastic Surgeon"),
    PODIATRIST("Podiatrist"),
    PSYCHIATRIST("Psychiatrist"),
    PULMONOLOGIST("Pulmonologist"),
    RADIOLOGIST("Radiologist"),
    RHEUMATOLOGIST("Rheumatologist"),
    SLEEP_MEDICINE_SPECIALIST("Sleep Medicine Specialist"),
    PARAMEDIC("Paramedic"),
    OPTOMETRIST("Optometrist"),
    DENTIST("Dentist"),
    DIAGNOSTIC_MEDICAL_SONOGRAPHER_SPECIALIST("Diagnostic Medical Sonographer Specialist"),
    CYSTOTECHNOLOGIST("Cystotechnologist"),
    UROLOGIST("Urologist"),
    AUDIOLOGIST("Audiologist"),
    DIRECTOR_OF_THE_RADIATION_DIVISION("Director of the Radiation Division"),
    DIRECTOR_OF_THE_LABORATORY_DIVISION("Director of the Laboratory Division"),
    ASSISTANT_DIRECTOR_OF_THE_TECHNICAL_DEPARTMENT("Assistant Director of the Technical Department"),
    DIRECTOR_OF_THE_PHARMACY_DIVISION("Director of the Pharmacy Division"),
    ASSISTANT_DIRECTOR_OF_THE_PHARMACY_DIVISION("Assistant Director of the Pharmacy Division"),
    DIRECTOR_OF_THE_TECHNICAL_DEPARTMENT("Director of the Technical Department"),
    NUTRITIONIST("Nutritionist"),
    ADMINISTRATION_MEMBER("Administration Member"),;

    private final String name;

    EnumDoctorRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }



    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }

    ;
}
