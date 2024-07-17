package com.social.social_media.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_ FOLLOW")
public class Follow implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFollow;

    @Enumerated(EnumType.STRING)
    private FollowStatus status;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User followerId;

    @ManyToOne
    @JoinColumn(name = "followee_id", nullable = false)
    private User followeeId;

    private LocalDateTime createdAt;

    public Follow() {};

    public Follow(FollowStatus status, User followerId, User followeedId) {
        this.status = status;
        this.followerId = followerId;
        this.followeeId = followeedId;
        this.createdAt = LocalDateTime.now();
    }

    public enum FollowStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        BLOCKED
    }

    public UUID getIdFollow() {
        return idFollow;
    }

    public FollowStatus getStatus() {
        return status;
    }

    public void setStatus(FollowStatus status) {
        this.status = status;
    }

    public User getFollowerId() {
        return followerId;
    }

    public void setFollowerId(User followerId) {
        this.followerId = followerId;
    }

    public User getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(User followeeId) {
        this.followeeId = followeeId;
    }
}
