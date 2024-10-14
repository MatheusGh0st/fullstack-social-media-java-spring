package com.social.social_media.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record StorieWithUserDTO(
        UUID storieId,
        String content,
        LocalDateTime expiresAt,
        UserDTO user) { }