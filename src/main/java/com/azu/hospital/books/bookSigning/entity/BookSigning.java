package com.azu.hospital.books.bookSigning.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.books.entity.Book;
import com.azu.hospital.utils.enums.book.SigningState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "book_signing")
@Getter
@Setter
public class BookSigning {

    @Id
    @SequenceGenerator(name = "book_signing_id_seq" , sequenceName = "book_signing_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_to_sign_id")
    private BaseUser usersToSign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signed_user_id")
    private BaseUser signedUsers;

    private SigningState state;



    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false)
    private Instant updateAt;

    public BookSigning() {
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