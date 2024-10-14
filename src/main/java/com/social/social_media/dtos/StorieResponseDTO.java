package com.social.social_media.dtos;

import com.social.social_media.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StorieResponseDTO {
    private UUID idStorie;
    private String content;
    private UserDTO user;
    private LocalDateTime createAt;
    private LocalDateTime expiresAt;

    // Getters and Setters
    public StorieResponseDTO() {}

    public StorieResponseDTO(UUID idStorie, String content, UserDTO user, LocalDateTime createAt, LocalDateTime expiresAt) {
        this.idStorie = idStorie;
        this.content = content;
        this.user = user;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
    }

    public UUID getIdStorie() {
        return idStorie;
    }

    public void setIdStorie(UUID idStorie) {
        this.idStorie = idStorie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}