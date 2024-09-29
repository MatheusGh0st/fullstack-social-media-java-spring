package com.social.social_media.service;

import com.social.social_media.dtos.*;
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

    public List<PostByUUIDs> getPostsByUserIds(List<UUID> ids) {
        // Fetch posts with likes count
        List<PostByUUIDs> posts = postRepository.findPostsByListUserIds(ids);

        // Extract post IDs to fetch comments
        List<UUID> postIds = posts.stream()
                .map(posy -> posy.post().getIdPost())
                .toList();

        // Fetch comments for each

        List<CommentWoutUserDTO> comments = postRepository.findCommentsByPostIds(postIds);


        // Map comments back to their respective posts
        for (PostByUUIDs posy : posts) {
            List<CommentWoutUserDTO> filteredComments = comments.stream()
                    .filter(comment -> comment.postId().equals(posy.post().getIdPost())) // Adjust this logic based on how you relate comments to posts
                    .toList();
            posy.comments().addAll(filteredComments); // Assuming you have a way to add comments back to PosyByUUIDs
        }

        return posts;
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

    public Page<PostsUsernameDTO> getPostsByUsername(String username, Pageable pageable) {
        if (username != null && !username.isEmpty()) {
            return postRepository.findAllPostsByUsername(username, pageable);
        }
        return Page.empty();
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
