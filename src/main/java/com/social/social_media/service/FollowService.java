package com.social.social_media.service;

import com.social.social_media.dtos.FollowRecordDTO;
import com.social.social_media.models.Follow;
import com.social.social_media.models.User;
import com.social.social_media.repositories.FollowRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Follow> getAllFollowers() {
        return followRepository.findAll();
    }

    public List<Follow> getFolloweeds(User user) {
        return followRepository.findAllByFolloweed(user);
    }

    public List<Follow> getAllFollowers(User user) {
        return followRepository.findAllByFollower(user);
    }

    public boolean isFollow(FollowRecordDTO followRecordDTO) {
        if (followRecordDTO.followerId().equals(followRecordDTO.followeedId())) {
            return false;
        }
        var userFollow = userRepository.findById(followRecordDTO.followerId());
        var userFollower = userRepository.findById(followRecordDTO.followeedId());

        boolean isFollowing = followRepository.existsByFollowerIdAndFolloweedId(userFollow.get(), userFollower.get());

        return isFollowing;
    }

    @Transactional
    public Follow addFollow(FollowRecordDTO followRecordDTO) {
        var userFollow = userRepository.findById(followRecordDTO.followerId());
        var userFollower = userRepository.findById(followRecordDTO.followeedId());
        if (userFollow.isEmpty() || userFollower.isEmpty()) {
            return null;
        }

        boolean followExists = followRepository.existsByFollowerIdAndFolloweedId(userFollow.get(), userFollower.get());

        if (userFollow.get().getIdUser().equals(userFollower.get().getIdUser())) {
            return null;
        }

        if (followExists) {
            return null;
        }

        var follow = new Follow(Follow.FollowStatus.ACCEPTED, userFollow.get(), userFollower.get());

        return followRepository.save(follow);
    }

    @Transactional
    public void deleteFollow(UUID id) {
        var follow = followRepository.findById(id);
        follow.ifPresent(value -> followRepository.delete(value));
    }
}
