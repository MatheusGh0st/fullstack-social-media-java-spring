package com.social.social_media.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponse {
    private final String token;
    private final UUID userId;

    public LoginResponse(String token, UUID userId) {
        this.token = token;
        this.userId = userId;
    }
}