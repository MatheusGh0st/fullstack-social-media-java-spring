package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserWithPostsAndFollowersDTO {
        @NotNull
        private UserDTO user;

        @NotNull
        private int followers;

        @NotNull
        private int following;

        public UserWithPostsAndFollowersDTO(UserDTO user, int followers, int following) {
                this.user = user;
                this.followers = followers;
                this.following = following;
        }

        // Default constructor
        public UserWithPostsAndFollowersDTO() {}
}