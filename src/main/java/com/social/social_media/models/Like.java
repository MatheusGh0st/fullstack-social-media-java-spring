package com.social.social_media.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_LIKE")
public class Like implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idLike;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    private LocalDateTime createAt;

    public Like() {};

    public Like(Post postId, Comment commentId, User userId) {
        this.postId = postId;
        this.commentId = commentId;
        this.userId = userId;
        this.createAt = LocalDateTime.now();
    }

    public UUID getIdLike() {
        return idLike;
    }

    public Post getPostId() {
        return postId;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public User getUserId() {
        return userId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
