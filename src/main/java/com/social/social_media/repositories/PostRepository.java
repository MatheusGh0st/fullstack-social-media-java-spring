package com.social.social_media.repositories;

import com.social.social_media.dtos.PostsMediaDTO;
import com.social.social_media.dtos.PostsUsernameDTO;
import com.social.social_media.models.Post;
import com.social.social_media.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT new com.social.social_media.dtos.PostsMediaDTO(p.idPost, p.imgUrl, p.createdAt, p.updateAt) FROM Post p WHERE p.user = :user")
    Page<PostsMediaDTO> findAllPostsByUserId(@Param("user") User user, Pageable pageable);

    @Query("SELECT new com.social.social_media.dtos.PostsUsernameDTO(p.idPost, p.imgUrl, p.description, p.createdAt, p.updateAt, COUNT(c), COUNT(l), new com.social.social_media.dtos.UserDTO(u.idUser, u.name, u.avatar, u.description, u.surname, u.username)) FROM Post p JOIN p.user u LEFT JOIN p.comments c LEFT JOIN p.likes l WHERE u.username = :username GROUP BY p.idPost, u.idUser")
    Page<PostsUsernameDTO> findAllPostsByUsername(@Param("username") String username, Pageable pageable);
}