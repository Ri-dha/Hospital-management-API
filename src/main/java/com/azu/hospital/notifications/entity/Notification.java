package com.azu.hospital.notifications.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )

    private Long id;

    private String title;

    private String content;

    private Boolean isSeen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private BaseUser sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private BaseUser receiver;

    @Column(updatable = false)
    @CurrentTimestamp
    private Instant createdAt;

    @Column(updatable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    private Long referenceId;


    public Notification() {
    }


    public Notification(
            String title,
            String content,
            Boolean isSeen,
            Long referenceId
    ) {
        this.title = title;
        this.content = content;
        this.isSeen = isSeen;
        this.referenceId = referenceId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
