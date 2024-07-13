package com.social.social_media.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
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

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Post() {};

    public Post(String description, String imgUrl, User user) {
        this.description = description;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}
