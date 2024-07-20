package com.social.social_media.repositories;

import com.social.social_media.models.Storie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorieRepository extends JpaRepository<Storie, UUID> {}