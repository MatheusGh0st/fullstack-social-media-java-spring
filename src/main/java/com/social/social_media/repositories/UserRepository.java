package com.social.social_media.repositories;

import com.social.social_media.dtos.UserWithPostsAndFollowersDTO;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    @Query("SELECT new com.social.social_media.dtos.UserWithPostsAndFollowersDTO(u.idUser, u.name, u.avatar, u.surname, u.email, u.city, u.school, u.work, p.idPost, p.imgUrl, p.createdAt, p.updateAt, COUNT(f) AS followersCount) " +
            "FROM User u " +
            "LEFT JOIN u.followers f " +
            "LEFT JOIN u.posts p " +
            "WHERE u.username = :username " +
            "GROUP BY u.idUser, p.idPost")
    List<UserWithPostsAndFollowersDTO> findUserWithPostsAndFollowers(@Param("username") String username);
}