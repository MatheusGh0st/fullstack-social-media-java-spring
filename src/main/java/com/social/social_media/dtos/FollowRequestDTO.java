package com.social.social_media.dtos;

import com.social.social_media.models.User;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FollowRequestDTO(@NotNull UUID senderId, @NotNull UUID recieverId) { }
