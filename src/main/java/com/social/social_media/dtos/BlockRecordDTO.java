package com.social.social_media.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlockRecordDTO(
        @NotNull UUID blockerId,
        @NotNull UUID blockedId
) {
}