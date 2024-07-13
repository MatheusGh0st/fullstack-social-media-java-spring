package com.social.social_media.controllers;

import com.social.social_media.dtos.PostRecordDto;
import com.social.social_media.models.Post;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST POST request successful");
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPostUserId() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(posts);
    }

    @PostMapping("/post")
    public ResponseEntity<Post> savePost(@RequestBody PostRecordDto postRecordDto) {
        var user = userRepository.findById(postRecordDto.userId()).orElseThrow();
        var post = new Post(postRecordDto.description(), postRecordDto.imgUrl(), user);

        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));
    }
}
