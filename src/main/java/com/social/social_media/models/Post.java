package com.social.social_media.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_POST")
@Data
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPost;
    private String description;
    private String imgUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Like> like;

    public Post() {}

    public Post(String description, String imgUrl, User user) {
        this.description = description;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}