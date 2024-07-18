package com.social.social_media.service;

import com.social.social_media.dtos.PostRecordDto;
import com.social.social_media.dtos.PostUpdateRecordDto;
import com.social.social_media.models.Post;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
