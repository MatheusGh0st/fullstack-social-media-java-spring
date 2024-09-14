package com.social.social_media.service;

import com.social.social_media.dtos.PostRecordDto;
import com.social.social_media.dtos.PostUpdateRecordDto;
import com.social.social_media.dtos.PostsMediaDTO;
import com.social.social_media.exceptions.UserNotFoundException;
import com.social.social_media.models.Post;
import com.social.social_media.models.User;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPostUser() {
        return postRepository.findAll();
    }

    public Page<PostsMediaDTO> getAllUserPosts(@PathVariable UUID userId, @RequestBody int page, @RequestBody int size) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }

        Pageable pageable = PageRequest.of(page, size);

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return postRepository.findAllPostsByUserId(user, pageable);
    }

    @Transactional
    public Post savePost(PostRecordDto postRecordDto) {
        var user = userRepository.findById(postRecordDto.userId());
        if (user.isEmpty()) {
            return null;
        }
        var post = new Post(postRecordDto.description(), postRecordDto.imgUrl(), user.get());

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(UUID id, PostUpdateRecordDto postUpdateRecordDto) {
        var post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setDescription(postUpdateRecordDto.description());
            post.get().setImgUrl(postUpdateRecordDto.imgUrl());
        }

        return postRepository.save(post.get());
    }

    @Transactional
    public void deletePost(UUID id) {
        var post = postRepository.findById(id);
        post.ifPresent(value -> postRepository.delete(value));
    }
}
