package com.social.social_media.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record StorieGetByDateOrUserStr(String dateTime,
                                       UUID userId) { }
