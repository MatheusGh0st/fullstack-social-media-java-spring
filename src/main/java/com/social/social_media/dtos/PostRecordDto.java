package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostRecordDto(
        @NotBlank String description,
        @NotBlank String imgUrl,
        @NotNull UUID userId
) {}
