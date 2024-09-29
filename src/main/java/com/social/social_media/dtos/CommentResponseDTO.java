package com.social.social_media.dtos;

import com.social.social_media.models.Like;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CommentResponseDTO {
        private UUID idComment;
        private String content;
        private LikesCommentsDTO likes;
        private boolean isLiked;
        private UserDTO user;

        public CommentResponseDTO(UUID idComment, String content, LikesCommentsDTO likes, boolean isLiked, UserDTO user) {
                this.idComment = idComment;
                this.content = content;
                this.likes = likes;
                this.isLiked = isLiked;
                this.user = user;
        }

        public UUID getIdComment() {
                return idComment;
        }

        public void setIdComment(UUID idComment) {
                this.idComment = idComment;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public LikesCommentsDTO getLikes() {
                return likes;
        }

        public void setLikes(LikesCommentsDTO likes) {
                this.likes = likes;
        }

        public boolean isLiked() {
                return isLiked;
        }

        public void setLiked(boolean liked) {
                isLiked = liked;
        }

        public UserDTO getUser() {
                return user;
        }

        public void setUser(UserDTO user) {
                this.user = user;
        }
}