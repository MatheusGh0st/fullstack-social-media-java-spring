package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRecordDto(
        @NotBlank String description,
        @NotBlank String imgUrl
) {}
