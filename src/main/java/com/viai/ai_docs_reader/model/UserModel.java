package com.viai.ai_docs_reader.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "timezone")
    private String timezone;

    @PrePersist
    private void prePersist() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        if(createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;

        if(timezone == null || timezone.isEmpty()) {
            timezone = "UTC";
        }
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
