package com.social.social_media.dtos;

import java.util.Optional;

public record UserEditDTO(
        Optional<String> firstName,
        Optional<String> surname,
        Optional<String> description,
        Optional<String> city,
        Optional<String> school,
        Optional<String> work,
        Optional<String> website
) {}
