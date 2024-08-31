package com.social.social_media.controllers;

import com.social.social_media.dtos.FollowRecordDTO;
import com.social.social_media.models.Follow;
import com.social.social_media.models.User;
import com.social.social_media.repositories.UserRepository;
import com.social.social_media.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/follows")
    public ResponseEntity<List<Follow>> getAllFollowers() {
        return ResponseEntity.status(HttpStatus.OK).body(followService.getAllFollowers());
    }

    @GetMapping("/followeeds/{id}")
    public ResponseEntity<List<Follow>> getAllFolloweeds(@PathVariable UUID id) {
        User user = userRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(followService.getFolloweeds(user));
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<List<Follow>> getAllFollowers(@PathVariable UUID id) {
        User user = userRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(followService.getAllFollowers(user));
    }

    @PostMapping("/follow")
    public ResponseEntity<Follow> addFollower(@RequestBody FollowRecordDTO followRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.addFollow(followRecordDTO));
    }

    @DeleteMapping("/follow/{id}")
    public ResponseEntity<String> unFollow(@PathVariable UUID id) {
        followService.deleteFollow(id);
        return ResponseEntity.status(HttpStatus.OK).body("Unfollow Profile Successfully");
    }
}