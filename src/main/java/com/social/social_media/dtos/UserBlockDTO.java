package com.social.social_media.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserBlockDTO(@NotNull UUID idUser, @NotBlank String name, @NotBlank String username) {}