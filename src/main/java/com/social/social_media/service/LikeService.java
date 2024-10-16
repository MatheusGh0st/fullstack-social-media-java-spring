package com.social.social_media.service;

import com.social.social_media.dtos.IsLikeDTO;
import com.social.social_media.dtos.IsLikeResponseDTO;
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

    public IsLikeResponseDTO isLikeExist(IsLikeDTO isLikeDTO) {
        var user = userRepository.findById(isLikeDTO.userId());
        var post = postRepository.findById(isLikeDTO.postId());
        if (user.isEmpty() || post.isEmpty()) {
            return new IsLikeResponseDTO(null, false);
        }
        Like like = null;
        List<Like> likes = null;
        if (isLikeDTO.commentId() != null) {
            var comment = commentRepository.findById(isLikeDTO.commentId());
            like = likeRepository.findByPostAndUserAndComment(post.get(), user.get(), comment.get());
            if (like == null) {
                return new IsLikeResponseDTO(null, false);
            }
        } else {
            likes = likeRepository.findByPostAndUser(post.get(), user.get());
        }

        boolean isLiked = likeRepository.existsByPostIdAndUserId(post.get(), user.get());

        if (like != null) {
            UUID likeId = like.getIdLike();
            return new IsLikeResponseDTO(likeId, isLiked);
        }

        if (likes != null) {
            UUID likeId = likes.stream()
                    .map(likeObj -> likeObj.getIdLike()) // Ensure this returns a String
                    .findFirst() // Use findFirst() to get an Optional<UUID>
                    .orElse(null); // Provide a default value if no element is found
            return new IsLikeResponseDTO(likeId, isLiked);
        }
        return new IsLikeResponseDTO(null, isLiked);
    }

    @Transactional
    public Like addLike(LikeRecordDTO likeRecordDTO) {
        var user = userRepository.findById(likeRecordDTO.userId());
        var post = postRepository.findById(likeRecordDTO.postId());
        if (likeRecordDTO.commentId() != null) {
            var comment = commentRepository.findById(likeRecordDTO.commentId());
            var like = new Like(post.get(), comment.get(), user.get());
            return likeRepository.save(like);
        }
        var like = new Like(post.get(), null, user.get());
        return likeRepository.save(like);
    }

    @Transactional
    public void removeLike(UUID id) {
        likeRepository.deleteById(id);
    }
}
