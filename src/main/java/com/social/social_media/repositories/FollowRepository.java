package com.social.social_media.repositories;

import com.social.social_media.dtos.FollowUUIDDTO;
import com.social.social_media.models.Follow;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {
    @Query("SELECT f FROM Follow f WHERE f.followeedId = :user")
    List<Follow> findAllByFolloweed(@Param("user") User user);
    @Query("SELECT f from Follow f WHERE f.followerId = :user")
    List<Follow> findAllByFollower(@Param("user") User user);

    boolean existsByFollowerIdAndFolloweedId(User followerId, User followeedId);

    @Query("SELECT f FROM Follow f WHERE f.followeedId = :user1 AND f.followerId = :user2")
    Follow findByFollowerIdAndFolloweedId(@Param("user1") User user1, @Param("user2") User user2);

    @Query("SELECT new com.social.social_media.dtos.FollowUUIDDTO(f.followerId.idUser) FROM Follow f WHERE f.followeedId = :userId")
    List<FollowUUIDDTO> findFollowingIdsByUserId(@Param("userId") User userId);
}