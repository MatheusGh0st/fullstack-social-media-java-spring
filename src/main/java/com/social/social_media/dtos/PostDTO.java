package com.social.social_media.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostDTO {
    private UUID idPost;
    private String description;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    // Constructor matching the JPQL query
    public PostDTO(UUID idPost, String description, String imgUrl, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.idPost = idPost;
        this.description = description;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public PostDTO() {};

    // Getters and Setters
    public UUID getIdPost() {
        return idPost;
    }

    public void setIdPost(UUID idPost) {
        this.idPost = idPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}