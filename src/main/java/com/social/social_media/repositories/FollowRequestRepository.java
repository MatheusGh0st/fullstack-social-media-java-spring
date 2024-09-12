package com.social.social_media.repositories;

import com.social.social_media.models.Follow;
import com.social.social_media.models.FollowRequest;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, UUID> {
    boolean existsBySenderAndReceiver(User senderId, User receiverId);

    @Query("SELECT f FROM FollowRequest f WHERE f.sender = :user1 AND f.receiver = :user2")
    FollowRequest findBySenderAndReceiver(@Param("user1") User user1, @Param("user2") User user2);
}