package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostsMediaDTO(
        @NotNull UUID idPost,
        @NotBlank String imgUrl,
        @NotBlank LocalDateTime createdAt,
        @NotBlank LocalDateTime updateAt) {}