package com.social.social_media.repositories;

import com.social.social_media.dtos.CommentResponseDTO;
import com.social.social_media.models.Comment;
import com.social.social_media.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("SELECT new com.social.social_media.dtos.CommentResponseDTO(c.idComment, c.content, null, false, " + // Set likes to null initially
            "new com.social.social_media.dtos.UserDTO(u.idUser, u.name, u.avatar, u.description, u.surname, u.username, u.email, u.city, u.school, u.work, u.website, u.createdAt)) " +
            "FROM Comment c " +
            "JOIN c.userId u " +
            "WHERE c.postId = :postId")
    List<CommentResponseDTO> findAllCommentsByPostId(@Param("postId") Post postId);
}