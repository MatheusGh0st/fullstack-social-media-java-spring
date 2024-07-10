package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name, @NotBlank String surname, @NotBlank String email, String description, @NotBlank String city, String school, String work, String website, @NotBlank String password) {}