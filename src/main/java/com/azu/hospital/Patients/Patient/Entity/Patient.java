package com.azu.hospital.Patients.Patient.Entity;

import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.Patient.request.*;
import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import com.azu.hospital.Patients.entryTable.entity.EntryTable;
import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.consultant.appointment.entity.Appointment;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.utils.enums.patient.BillState;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.image.Image;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_seq",
            sequenceName = "patient_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_seq"
    )
    private Long id;

    @Embedded
    @Valid
    private JobInfo jobInfo;

    @Embedded
    @Valid
    private PatientAddress patientAddress;

    @Embedded
    @Valid
    private PatientContact patientContact;

    @Embedded
    @Valid
    private PatientData patientData;

    @Embedded
    @Valid
    private PatientDate patientDate;

    @Embedded
    @Valid
    private PatientMedicalInfo patientMedicalInfo;


    @Column(columnDefinition = "TEXT")
    private String image;

    private BillState billState = BillState.UNPAID;


    /**
     * main relation
     **/
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("state DESC ")
    private List<PatientDoctor> doctor = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Permanent permanentDoctor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> tests = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> labs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> ecgs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> radiologies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> ultrasounds = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<BaseCharts> baseCharts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private List<Appointment> appointments;
    /** End main relation **/


    /**
     * Charts relation
     **/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @OrderBy("COALESCE(updateAt, daysOffWritten)")
    private List<MedicalHistory> medicalHistory;

    /** End Charts relation **/


    /**
     * Start Ward And Beds relation
     **/

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Bed bed;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientExpanseList> patientExpanses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<DrugRequestHandlerList> drugRequestHandlerLists;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private PatientWallet patientWallet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PrematureBaby> prematureBabies = new ArrayList<>();

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updateAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<PatientDrugsExpanseResultTable> patients = new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    private Boolean isArchived = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<EntryTable> entryTables;

    private Long entryNo = 1L;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;

    public Patient(Builder builder) {
        this.jobInfo = builder.patient.jobInfo;
        this.patientAddress = builder.patient.patientAddress;
        this.patientContact = builder.patient.patientContact;
        this.patientData = builder.patient.patientData;
        this.patientDate = builder.patient.patientDate;
        this.patientMedicalInfo = builder.patient.patientMedicalInfo;
    }

    public Patient(Long id, JobInfo jobInfo, PatientAddress patientAddress, PatientContact patientContact,
                   PatientData patientData, PatientDate patientDate, PatientMedicalInfo patientMedicalInfo,
                   String image, BillState billState, List<PatientDoctor> doctor, Permanent permanentDoctor,
                   List<Image> images, List<File> files, List<File> tests, List<File> labs, List<File> ecgs,
                   List<File> radiologies, List<File> ultrasounds, List<BaseCharts> baseCharts, List<Appointment> appointments,
                   List<MedicalHistory> medicalHistory, Ward ward, Bed bed, List<PatientExpanseList> patientExpanses,
                   List<DrugRequestHandlerList> drugRequestHandlerLists, PatientWallet patientWallet, Instant createdAt,
                   Instant updateAt, List<PatientDrugsExpanseResultTable> patients, Boolean isArchived,
                   List<EntryTable> entryTables, Long entryNo) {
        this.id = id;
        this.jobInfo = jobInfo;
        this.patientAddress = patientAddress;
        this.patientContact = patientContact;
        this.patientData = patientData;
        this.patientDate = patientDate;
        this.patientMedicalInfo = patientMedicalInfo;
        this.image = image;
        this.billState = billState;
        this.doctor = doctor;
        this.permanentDoctor = permanentDoctor;
        this.images = images;
        this.files = files;
        this.tests = tests;
        this.labs = labs;
        this.ecgs = ecgs;
        this.radiologies = radiologies;
        this.ultrasounds = ultrasounds;
        this.baseCharts = baseCharts;
        this.appointments = appointments;
        this.medicalHistory = medicalHistory;
        this.ward = ward;
        this.bed = bed;
        this.patientExpanses = patientExpanses;
        this.drugRequestHandlerLists = drugRequestHandlerLists;
        this.patientWallet = patientWallet;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.patients = patients;
        this.isArchived = isArchived;
        this.entryTables = entryTables;
        this.entryNo = entryNo;
    }

    public Patient() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobInfo getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    public PatientAddress getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(PatientAddress patientAddress) {
        this.patientAddress = patientAddress;
    }

    public PatientContact getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(PatientContact patientContact) {
        this.patientContact = patientContact;
    }

    public PatientData getPatientData() {
        return patientData;
    }

    public void setPatientData(PatientData patientData) {
        this.patientData = patientData;
    }

    public PatientDate getPatientDate() {
        return patientDate;
    }

    public void setPatientDate(PatientDate patientDate) {
        this.patientDate = patientDate;
    }

    public PatientMedicalInfo getPatientMedicalInfo() {
        return patientMedicalInfo;
    }

    public void setPatientMedicalInfo(PatientMedicalInfo patientMedicalInfo) {
        this.patientMedicalInfo = patientMedicalInfo;
    }

    public List<File> getTests() {
        return tests;
    }

    public void setTests(List<File> tests) {
        this.tests = tests;
    }

    public List<PatientDoctor> getDoctor() {
        return doctor;
    }

    public PatientDoctor getDoctorSpecials() {
        return doctor.stream().findFirst().orElse(null);
    }

    public void setDoctor(List<PatientDoctor> doctor) {
        this.doctor = doctor;
    }

    public Permanent getPermanentDoctor() {
        return permanentDoctor;
    }

    public void setPermanentDoctor(Permanent permanentDoctor) {
        this.permanentDoctor = permanentDoctor;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<MedicalHistory> getMedicalHistory() {
        return medicalHistory;
    }


    public String getAllergySpecial() {
        return medicalHistory
                .stream()
                .map(MedicalHistory::getDrugHistoryAllergy)
                .findFirst()
                .orElse(null);
    }

    public String getDxSpecial() {
        return medicalHistory
                .stream()
                .map(MedicalHistory::getDx)
                .findFirst()
                .orElse(null);
    }

    public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public MedicalHistory lastMedicalHistory() {
        if (getIsHasMedicalHistory()) {
            return this.medicalHistory.get(this.medicalHistory.size() - 1);
        }
        return null;
    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Boolean getIsHasMedicalHistory() {
        return medicalHistory != null && medicalHistory.size() != 0;

    }

    public static class Builder {

        Patient patient = new Patient();

        public Builder withJobInfo(JobInfo jobInfo) {
            patient.jobInfo = jobInfo;
            return this;
        }

        public Builder withPatientAddress(PatientAddress patientAddress) {
            patient.patientAddress = patientAddress;
            return this;
        }

        public Builder withPatientContact(PatientContact patientContact) {
            patient.patientContact = patientContact;
            return this;
        }

        public Builder withPatientDate(PatientDate patientDate) {
            patient.patientDate = patientDate;
            return this;
        }

        public Builder withPatientData(PatientData patientData) {
            patient.patientData = patientData;
            return this;
        }

        public Builder withPatientMedicalInfo(PatientMedicalInfo patientMedicalInfo) {
            patient.patientMedicalInfo = patientMedicalInfo;
            return this;
        }

//        public Builder withJobInfoUpdate(updateJobInfo jobInfo) {
//            patient.jobInfo = jobInfo;
//            return this;
//        }
//
//        public Builder withPatientAddressUpdate(updatePatientAddresse patientAddress) {
//            patient.patientAddress = patientAddress;
//            return this;
//        }
//
//        public Builder withPatientContactUpdate(updatePatientContact patientContact) {
//            patient.patientContact = patientContact;
//            return this;
//        }
//
//        public Builder withPatientDateUpdate(updatePatientDate patientDate) {
//            patient.patientDate = patientDate;
//            return this;
//        }
//
//        public Builder withPatientData(updatePatientData patientData) {
//            patient.patientData = patientData;
//            return this;
//        }
//
//        public Builder withPatientMedicalInfoUpdate(updatePatientMedicalInfo patientMedicalInfo) {
//            patient.patientMedicalInfo = patientMedicalInfo;
//            return this;
//        }


        public Patient build() {
            return new Patient(this);
        }


    }


}
