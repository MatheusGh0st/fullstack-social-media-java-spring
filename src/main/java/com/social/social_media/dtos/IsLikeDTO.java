package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record IsLikeDTO(@NotBlank UUID postId, @NotBlank UUID userId) { }
