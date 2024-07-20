package com.social.social_media.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_STORIE")
public class Storie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idStorie;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    private String content;

    private LocalDateTime createAt;
    private LocalDateTime expiresAt;

    public Storie() {};

    public Storie(String content, User user, LocalDateTime expiresAt) {
        this.content = content;
        this.userId = user;
        this.createAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
    }

    public UUID getIdStorie() {
        return idStorie;
    }

    public User getUser() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}