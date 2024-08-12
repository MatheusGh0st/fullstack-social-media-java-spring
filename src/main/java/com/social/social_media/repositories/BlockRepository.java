package com.social.social_media.repositories;

import com.social.social_media.models.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {}
