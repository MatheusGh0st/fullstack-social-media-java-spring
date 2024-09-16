package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record FollowRequestReceiverDTO(@NotBlank UUID receiverId) {
}
