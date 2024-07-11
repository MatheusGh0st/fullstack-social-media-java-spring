package com.social.social_media.repositories;

import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    User findByEmail(String email);
}