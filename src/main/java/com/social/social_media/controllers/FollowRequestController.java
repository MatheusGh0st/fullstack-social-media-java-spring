package com.social.social_media.controllers;

import com.social.social_media.dtos.FollowRequestDTO;
import com.social.social_media.dtos.FollowRequestReceiverDTO;
import com.social.social_media.dtos.FollowRequestReceiverResponseDTO;
import com.social.social_media.dtos.FollowRequestResponseDTO;
import com.social.social_media.models.FollowRequest;
import com.social.social_media.repositories.UserRepository;
import com.social.social_media.service.FollowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FollowRequestController {
    @Autowired
    FollowRequestService followRequestService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/followsReceivers")
    public ResponseEntity<List<FollowRequestReceiverResponseDTO>> getAllFollowsReceivers(@RequestBody FollowRequestReceiverDTO followRequestReceiverDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(followRequestService.getAllByReceiver(followRequestReceiverDTO.receiverId()));
    }

    @PostMapping("/isfollowRequest")
    public ResponseEntity<FollowRequestResponseDTO> isFollowRequest(@RequestBody FollowRequestDTO followRequestDTO) {
        System.out.println(followRequestDTO);
        var followRequestObj = followRequestService.isFollowRequestExists(followRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(followRequestObj);
    }

    @PostMapping("/followRequest")
    public ResponseEntity<FollowRequest> addFollowRequest(@RequestBody FollowRequestDTO followRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(followRequestService.addFollowRequest(followRequestDTO));
    }

    @DeleteMapping("/followRequest/{id}")
    public ResponseEntity<String> deleteFollowRequest(@PathVariable UUID id) {
        followRequestService.deleteFollowRequest(id);
        return ResponseEntity.status(HttpStatus.OK).body("Follow request deleted successfully");
    }
}
