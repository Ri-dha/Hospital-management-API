package com.azu.hospital.books.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.attachments.entity.Attachments;
import com.azu.hospital.books.bookSigning.entity.BookSigning;
import com.azu.hospital.books.copyTo.entity.CopyTo;
import com.azu.hospital.hospital_info.entity.HospitalInfo;
import com.azu.hospital.utils.enums.book.BookState;
import com.azu.hospital.utils.enums.book.BookType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @SequenceGenerator(name = "book_id_seq" , sequenceName = "book_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String bookBody;

    @Column(columnDefinition = "TEXT")
    private String directorateName;

    @Column(columnDefinition = "TEXT")
    private String directorateNameTo;

    private BookState state;

    private String importNumber;

    private String exportNumber;


    private BookType type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_owner_id")
    private BaseUser bookOwner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private HospitalInfo hospitalInfo;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attachments> attachments;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CopyTo> copyTo;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookSigning> bookSignings;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updateAt;




    public Book() {
    }





    public Book(String title, String bookBody, BookState bookState, BookType type, String directorateName, String directorateNameTo, String importNumber, String exportNumber) {
        this.title = title;
        this.bookBody = bookBody;
        this.state = bookState;
        this.type = type;
        this.directorateName = directorateName;
        this.directorateNameTo = directorateNameTo;
        this.importNumber = importNumber;
        this.exportNumber = exportNumber;
    }

    public Book(String title, String bookBody, BookState bookState, String directorateName, String directorateNameTo) {
        this.title = title;
        this.bookBody = bookBody;
        this.state = bookState;
        this.directorateName = directorateName;
        this.directorateNameTo = directorateNameTo;
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
