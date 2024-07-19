package com.social.social_media.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "TB_COMMENT")
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idComment;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    private String content;
    private LocalTime createAt;

    public Comment() {}

    public Comment(Post postId, User userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createAt = LocalTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public User getUserId() {
        return userId;
    }

    public Post getPostId() {
        return postId;
    }

    public UUID getIdComment() {
        return idComment;
    }
}
