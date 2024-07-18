package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentRecordDTO(
        @NotNull UUID postId,
        @NotNull UUID userId,
        @NotBlank String content
) {}