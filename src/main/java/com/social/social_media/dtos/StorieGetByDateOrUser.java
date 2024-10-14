package com.social.social_media.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record StorieGetByDateOrUser(
        LocalDateTime dateTime,
        UUID userId) { }
