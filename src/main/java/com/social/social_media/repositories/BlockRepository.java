package com.social.social_media.repositories;

import com.social.social_media.dtos.BlockDTO;
import com.social.social_media.models.Block;
import com.social.social_media.models.Follow;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {
    boolean existsByBlockerAndBlocked(User blocker, User blocked);

    @Query("SELECT b FROM Block b WHERE b.blocker = :user1 AND b.blocked = :user2")
    Optional<Block> findByBlockerIdAndBlockedId(@Param("user1") User user1, @Param("user2") User user2);
}