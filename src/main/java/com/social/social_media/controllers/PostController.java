package com.social.social_media.controllers;

import com.social.social_media.dtos.PostRecordDto;
import com.social.social_media.dtos.PostUpdateRecordDto;
import com.social.social_media.models.Post;
import com.social.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPostUserId() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostUser());
    }

    @PostMapping("/post")
    public ResponseEntity<Post> savePost(@RequestBody PostRecordDto postRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(postRecordDto));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody PostUpdateRecordDto postUpdateRecordDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postService.updatePost(id, postUpdateRecordDto));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post delete successfully");
    }
}
