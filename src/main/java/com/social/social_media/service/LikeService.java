package com.social.social_media.service;

import com.social.social_media.dtos.LikeRecordDTO;
import com.social.social_media.models.Like;
import com.social.social_media.repositories.CommentRepository;
import com.social.social_media.repositories.LikeRepository;
import com.social.social_media.repositories.PostRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Transactional
    public Like addLike(LikeRecordDTO likeRecordDTO) {
        var user = userRepository.findById(likeRecordDTO.userId());
        var post = postRepository.findById(likeRecordDTO.postId());
        var comment = commentRepository.findById(likeRecordDTO.commentId());
        if (user.isEmpty() || post.isEmpty() || comment.isEmpty()) { return null; }
        var like = new Like(post.get(), comment.get(), user.get());
        return likeRepository.save(like);
    }

    @Transactional
    public void removeLike(UUID id) {
        likeRepository.deleteById(id);
    }
}
