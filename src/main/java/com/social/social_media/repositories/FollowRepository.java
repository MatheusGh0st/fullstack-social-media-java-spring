package com.social.social_media.repositories;

import com.social.social_media.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {}