package com.social.social_media.repositories;

import com.social.social_media.dtos.LikesCommentsDTO;
import com.social.social_media.models.Comment;
import com.social.social_media.models.Like;
import com.social.social_media.models.Post;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {
    @Query("SELECT l FROM Like l WHERE l.postId = :post AND l.userId = :user")
    List<Like> findByPostAndUser(Post post, User user);

    @Query("SELECT l FROM Like l WHERE l.postId = :post AND l.userId = :user AND l.commentId = :comment")
    Like findByPostAndUserAndComment(Post post, User user, Comment comment);

    boolean existsByPostIdAndUserId(Post postId, User userId);
    boolean existsByPostIdAndUserIdAndCommentId(Post postId, User userId, Comment commentId);

    @Query("SELECT new com.social.social_media.dtos.LikesCommentsDTO(COUNT(l)) FROM Like l WHERE l.commentId = :commentId")
    LikesCommentsDTO findLikesByCommentId(@Param("commentId") Comment commentId);
}