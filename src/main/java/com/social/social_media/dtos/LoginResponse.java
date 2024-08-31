package com.social.social_media.dtos;

public record LoginResponse(String token, UserWithPostsAndFollowersDTO user) {}