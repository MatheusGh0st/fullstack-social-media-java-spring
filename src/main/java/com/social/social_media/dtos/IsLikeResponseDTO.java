package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record IsLikeResponseDTO(@NotBlank UUID likeId, @NotBlank boolean isLiked) {}