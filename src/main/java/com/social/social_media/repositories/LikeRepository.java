package com.social.social_media.repositories;

import com.social.social_media.models.Like;
import com.social.social_media.models.Post;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {
    @Query("SELECT l FROM Like l WHERE l.postId = :post AND l.userId = :user")
    Like findByPostAndUser(Post post, User user);

    boolean existsByPostIdAndUserId(Post postId, User userId);
}