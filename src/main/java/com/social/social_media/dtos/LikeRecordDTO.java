package com.social.social_media.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LikeRecordDTO(
        @NotNull UUID userId,
        @NotNull UUID postId,
        @NotNull UUID commentId
) {}