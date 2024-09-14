package com.social.social_media.repositories;

import com.social.social_media.dtos.PostsMediaDTO;
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
}