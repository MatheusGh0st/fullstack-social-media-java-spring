package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public record PostByUUIDs(
        @NotBlank PostDTO post,
        @NotBlank UserDTO user,
        @NotBlank Long likesCount,
        @NotBlank Long commentsCount,
        @NotBlank List<CommentWoutUserDTO> comments
) {
    public PostByUUIDs(PostDTO postDto, UserDTO userDTO, Long likesCount, Long commentCount) {
        this(postDto, userDTO, likesCount, commentCount, new ArrayList<>());
    }
}