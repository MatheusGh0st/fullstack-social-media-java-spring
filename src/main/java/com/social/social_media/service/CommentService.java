package com.social.social_media.service;

import com.social.social_media.dtos.*;
import com.social.social_media.models.Comment;
import com.social.social_media.models.Like;
import com.social.social_media.repositories.CommentRepository;
import com.social.social_media.repositories.LikeRepository;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<CommentResponseDTO> getCommentsWithLikes(UUID postId) {
        var postObj = postRepository.findById(postId);
        if (postObj != null) {
            List<CommentResponseDTO> comments = commentRepository.findAllCommentsByPostId(postObj.get());

            for (CommentResponseDTO comment : comments) {
                var commentObj = commentRepository.findById(comment.getIdComment());
                var userId = comment.getUser().getIdUser();
                var userObj = userRepository.findById(userId);
                var isLiked = likeRepository.existsByPostIdAndUserIdAndCommentId(postObj.get(), userObj.get(), commentObj.get());
                comment.setLiked(isLiked);
                LikesCommentsDTO likes = likeRepository.findLikesByCommentId(commentObj.get());
                comment.setLikes(likes); // Set the likes for each comment
            }
            return comments;
        }

        return null;
    }

    @Transactional
    public CommentResponseDTO addComment(CommentRecordDTO commentRecordDTO) {
        var user = userRepository.findById(commentRecordDTO.userId());
        var postObj = postRepository.findById(commentRecordDTO.postId());
        if (user.isEmpty() || postObj.isEmpty()) {
            return null;
        }
        var comment = new Comment(postObj.get(),  user.get(), commentRecordDTO.content());

        var commentObj = commentRepository.save(comment);

        UserDTO userDto = getUserDTO(commentObj, comment);

        List<PostDTO> postDTOs = commentObj.getUserId().getPosts().stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setDescription(post.getDescription());
            postDTO.setImgUrl(post.getImgUrl());
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setUpdateAt(post.getUpdateAt());
            return postDTO;
        }).collect(Collectors.toList());

        var comment1 = commentRepository.findById(comment.getIdComment());
        var userId = comment.getUserId().getIdUser();
        var userObj = userRepository.findById(userId);
        var isLiked = likeRepository.existsByPostIdAndUserIdAndCommentId(postObj.get(), userObj.get(), comment1.get());

        LikesCommentsDTO newLike = Optional.ofNullable(commentObj)
                .map(Comment::getLikes) // Assuming Comment is your class with getLikes method
                .map(likes -> new LikesCommentsDTO((long) likes.size()))
                .orElse(null); // Or handle it differently if you prefer


        userDto.setPosts(postDTOs);
        return new CommentResponseDTO(commentObj.getIdComment(), commentObj.getContent(), newLike, isLiked, userDto);
    }

    private static UserDTO getUserDTO(Comment commentObj, Comment comment) {
        UserDTO userDto = new UserDTO();
        userDto.setIdUser(commentObj.getUserId().getIdUser());
        userDto.setName(comment.getUserId().getName());
        userDto.setDescription(comment.getUserId().getDescription());
        userDto.setWebsite(comment.getUserId().getWebsite());
        userDto.setSurname(comment.getUserId().getSurname());
        userDto.setAvatar(comment.getUserId().getAvatar());
        userDto.setEmail(comment.getUserId().getEmail());
        userDto.setCity(comment.getUserId().getCity());
        userDto.setSchool(comment.getUserId().getSchool());
        userDto.setWork(comment.getUserId().getWork());
        userDto.setUsername(comment.getUserId().getUsername());
        userDto.setCreatedAt(comment.getUserId().getCreatedAt());
        return userDto;
    }

    @Transactional
    public Comment editComment(UUID id, CommentUpdateRecordDTO commentUpdate) {
        var comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return null;
        }
        comment.get().setContent(commentUpdate.newContent());

        return commentRepository.save(comment.get());
    }

    @Transactional
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
