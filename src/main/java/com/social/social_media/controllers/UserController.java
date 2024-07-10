package com.social.social_media.controllers;

import com.social.social_media.dtos.UserRecordDto;
import com.social.social_media.models.User;
import com.social.social_media.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var encoder = new BCryptPasswordEncoder();
        var hashedPassword = encoder.encode(userRecordDto.password());

        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user, "password");

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }
}