package com.social.social_media.service;

import com.social.social_media.dtos.CommentRecordDTO;
import com.social.social_media.dtos.CommentUpdateRecordDTO;
import com.social.social_media.models.Comment;
import com.social.social_media.repositories.CommentRepository;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Transactional
    public Comment addComment(CommentRecordDTO commentRecordDTO) {
        var user = userRepository.findById(commentRecordDTO.userId());
        var post = postRepository.findById(commentRecordDTO.postId());
        if (user.isEmpty() || post.isEmpty()) {
            return null;
        }
        var comment = new Comment(post.get(),  user.get(), commentRecordDTO.content());

        return commentRepository.save(comment);
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
