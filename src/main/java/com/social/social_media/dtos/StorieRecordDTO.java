package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record StorieRecordDTO(
        @NotNull UUID userId,
        @NotBlank String content,
        @NotBlank LocalDateTime expiresAt
) {}