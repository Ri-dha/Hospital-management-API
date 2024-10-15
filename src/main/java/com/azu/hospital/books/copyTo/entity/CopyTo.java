package com.azu.hospital.books.copyTo.entity;


import com.azu.hospital.books.entity.Book;
import com.azu.hospital.bulding.department.entity.Department;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "copy_to")
public class CopyTo {
    @Id
    @SequenceGenerator(name = "copy_to_id_seq" , sequenceName = "copy_to_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Column(updatable = false)
    private Instant createdAt;


    private Instant updateAt;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


    public CopyTo() {
    }
    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdateAt() {
        this.updateAt = Instant.now();
    }

}
