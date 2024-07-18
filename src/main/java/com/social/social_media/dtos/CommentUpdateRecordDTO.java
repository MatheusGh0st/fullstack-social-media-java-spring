package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRecordDTO(
        @NotBlank String newContent
) {}
