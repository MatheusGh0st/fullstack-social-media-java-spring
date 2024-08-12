package com.social.social_media.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_BLOCK", uniqueConstraints = @UniqueConstraint(columnNames = {"blocker_id", "blocked_id"}))
public class Block implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBlock;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "blocker_id", nullable = false)
    private User blocker;

    @ManyToOne
    @JoinColumn(name = "blocked_id", nullable = false)
    private User blocked;

    public Block() {};

    public Block(User blocker, User blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getIdBlock() {
        return idBlock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getBlocker() {
        return blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    public User getBlocked() {
        return blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }
}
