package com.azu.hospital.books.attachments.entity;


import com.azu.hospital.books.entity.Book;
import com.azu.hospital.utils.files.File;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "attachments")
public class Attachments {
    @Id
    @SequenceGenerator(name = "attachments_id_seq" , sequenceName = "attachments_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String Name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

    @Column(insertable = false)
    private Instant createdAt;

    @Column(updatable = false)
    private Instant updateAt;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Attachments(String name) {
        Name = name;
    }


    public Attachments() {
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
