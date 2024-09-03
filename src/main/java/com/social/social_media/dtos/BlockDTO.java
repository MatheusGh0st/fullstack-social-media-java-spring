package com.social.social_media.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlockDTO(
        @NotNull UUID idBlock,
        @NotNull UserBlockDTO blocker,
        @NotNull UserBlockDTO blocked
) {}
