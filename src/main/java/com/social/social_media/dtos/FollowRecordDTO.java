package com.social.social_media.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FollowRecordDTO(
        @NotNull UUID followerId,
        @NotNull UUID followeedId
) {}