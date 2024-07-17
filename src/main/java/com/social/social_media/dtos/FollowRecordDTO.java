package com.social.social_media.dtos;

import com.social.social_media.models.Follow;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FollowRecordDTO(
        @NotNull UUID followerId,
        @NotNull UUID followeeId
) {}