package com.social.social_media.repositories;

import com.social.social_media.dtos.FollowRequestReceiverResponseDTO;
import com.social.social_media.models.FollowRequest;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, UUID> {
    boolean existsBySenderAndReceiver(User senderId, User receiverId);

    @Query("SELECT f FROM FollowRequest f WHERE f.sender = :user1 AND f.receiver = :user2")
    FollowRequest findBySenderAndReceiver(@Param("user1") User user1, @Param("user2") User user2);

    @Query("SELECT new com.social.social_media.dtos.FollowRequestReceiverResponseDTO(f.idFollowRequest, f.createdAt, new com.social.social_media.dtos.UserDTO(f.sender.idUser, f.sender.name, f.sender.avatar, f.sender.description, f.sender.surname, f.sender.username, f.sender.email, f.sender.city, f.sender.school, f.sender.work, f.sender.website, f.sender.createdAt)) FROM FollowRequest f WHERE f.receiver = :user")
    List<FollowRequestReceiverResponseDTO> findAllByReceiverId(@Param("user") User user);
}