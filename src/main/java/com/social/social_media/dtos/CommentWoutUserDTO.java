package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CommentWoutUserDTO(@NotBlank UUID idComment, @NotBlank String content, @NotBlank UUID postId) {}
