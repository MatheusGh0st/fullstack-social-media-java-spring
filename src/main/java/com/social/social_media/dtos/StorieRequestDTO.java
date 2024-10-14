package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record StorieRequestDTO(
        @NotBlank String content,
        @NotBlank String expiresAt,
        @NotBlank UUID userId) { }