package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserWithPostsAndFollowersDTO {
        @NotNull
        private UUID idUser;

        @NotBlank
        private String name;

        @NotBlank
        private String avatar;

        @NotBlank
        private String surname;

        @NotBlank
        private String email;

        @NotBlank
        private String city;

        @NotBlank
        private String school;

        @NotBlank
        private String work;

        @NotNull
        private UUID idPost;

        @NotBlank
        private String imgUrl;

        @NotNull
        private LocalDateTime createdAt; // Post createdAt field
        @NotNull
        private LocalDateTime updatedAt; // Post updatedAt field

        @NotNull
        private Long followersCount;

        // Constructor matching the JPQL query
        public UserWithPostsAndFollowersDTO(UUID idUser, String name, String avatar, String surname, String email, String city, String school, String work, UUID idPost, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, Long followersCount) {
                this.idUser = idUser;
                this.name = name;
                this.avatar = avatar;
                this.surname = surname;
                this.email = email;
                this.city = city;
                this.school = school;
                this.work = work;
                this.idPost = idPost;
                this.imgUrl = imgUrl;
                this.createdAt = createdAt; // Initialize createdAt from Post
                this.updatedAt = updatedAt; // Initialize updatedAt from Post
                this.followersCount = followersCount;
        }

        // Default constructor
        public UserWithPostsAndFollowersDTO() {}
}