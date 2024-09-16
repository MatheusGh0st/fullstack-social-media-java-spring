package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record FollowRequestReceiverResponseDTO(
        @NotBlank UUID idFollowRequest,
        @NotBlank LocalDateTime createdAt,
        @NotBlank UserDTO sender) {}