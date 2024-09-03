package com.social.social_media.repositories;

import com.social.social_media.dtos.BlockDTO;
import com.social.social_media.models.Block;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {
    @Query("SELECT new com.social.social_media.dtos.BlockDTO(b.idBlock, new com.social.social_media.dtos.UserBlockDTO(b.blocker.idUser, b.blocker.name, b.blocker.username), new com.social.social_media.dtos.UserBlockDTO(b.blocked.idUser, b.blocked.name, b.blocked.username)) FROM Block b WHERE b.blocked = :user")
    Optional<BlockDTO> isBlocked(@Param("user") User user);
}
