package com.social.social_media.controllers;

import com.social.social_media.dtos.LikeRecordDTO;
import com.social.social_media.models.Like;
import com.social.social_media.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/likes")
    public ResponseEntity<List<Like>> getAllLikes() {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.getAllLikes());
    }

    @PostMapping("/like")
    public ResponseEntity<Like> addLike(@RequestBody LikeRecordDTO likeRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.addLike(likeRecordDTO));
    }

    @DeleteMapping("/like/{id}")
    public ResponseEntity<String> dislike(@PathVariable UUID id) {
        likeService.removeLike(id);
        return ResponseEntity.status(HttpStatus.OK).body("Dislike Successfully");
    }
}
