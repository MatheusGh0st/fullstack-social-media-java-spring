package com.social.social_media.service;

import com.social.social_media.dtos.FollowRequestDTO;
import com.social.social_media.dtos.FollowRequestResponseDTO;
import com.social.social_media.models.FollowRequest;
import com.social.social_media.repositories.FollowRequestRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowRequestService {
    @Autowired
    FollowRequestRepository followRequestRepository;

    @Autowired
    UserRepository userRepository;

    public FollowRequestResponseDTO isFollowRequestExists(FollowRequestDTO followRequestDTO) {
        if (followRequestDTO.recieverId().equals(followRequestDTO.senderId())) {
            return new FollowRequestResponseDTO(null, false);
        }
        var userReciever = userRepository.findById(followRequestDTO.recieverId());
        var userSender = userRepository.findById(followRequestDTO.senderId());

        var followRequestId = followRequestRepository.findBySenderAndReceiver(userSender.get(), userReciever.get());

        boolean isfollowRequestExist = followRequestRepository.existsBySenderAndReceiver(userSender.get(), userReciever.get());

        return new FollowRequestResponseDTO(followRequestId.getIdFollowRequest(), isfollowRequestExist);
    }

    @Transactional
    public FollowRequest addFollowRequest(FollowRequestDTO followRequestDTO) {
        var userReciever = userRepository.findById(followRequestDTO.recieverId());
        var userSender = userRepository.findById(followRequestDTO.senderId());
        if (userReciever.isEmpty() || userSender.isEmpty()) {
            return null;
        }

        boolean followRequestExists = followRequestRepository.existsBySenderAndReceiver(userReciever.get(), userSender.get());

        if (userReciever.get().getIdUser().equals(userSender.get().getIdUser())) {
            return null;
        }

        if (followRequestExists) {
            return null;
        }

        var followRequest = new FollowRequest(userReciever.get(), userSender.get());

        return followRequestRepository.save(followRequest);
    }

    @Transactional
    public void deleteFollowRequest(UUID id) {
        var follow = followRequestRepository.findById(id);
        follow.ifPresent(value -> followRequestRepository.delete(value));
    }
}
