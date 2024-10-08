package com.social.social_media.repositories;

import com.social.social_media.models.Storie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorieRepository extends JpaRepository<Storie, UUID> {}