package com.social.social_media.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}