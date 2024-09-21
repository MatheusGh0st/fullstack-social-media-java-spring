package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostsUsernameDTO(
        @NotBlank UUID idPost,
        @NotBlank String imgUrl,
        @NotBlank String description,
        @NotBlank LocalDateTime createdAt,
        @NotBlank LocalDateTime updateAt,
        @NotBlank Long commentsCount,
        @NotBlank Long likesCount,
        @NotBlank UserDTO user
) {}
