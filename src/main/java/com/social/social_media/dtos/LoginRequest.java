package com.social.social_media.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public Object getPassword() {
        return this.password;
    }

    public Object getEmail() {
        return this.email;
    }
}