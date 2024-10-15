package com.azu.hospital.ph.Sales.customer.entity;


import com.azu.hospital.utils.enums.Gender;
import jakarta.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "patient_id_seq",
            sequenceName = "patient_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_seq"
    )
    private Long customerId;
    private String name;
    private String age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String contact;
    private String doctorName;

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    private List<SoldBill> billList = new ArrayList<>();


    public Customer() {
    }

    public Customer(Long customerId, String name, String age, Gender gender, String contact, String doctorName) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.doctorName = doctorName;
    }

    public Customer(String name, String age, Gender gender, String contact, String doctorName) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.doctorName = doctorName;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
