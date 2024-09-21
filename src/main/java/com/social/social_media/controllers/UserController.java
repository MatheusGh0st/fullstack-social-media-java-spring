package com.social.social_media.controllers;

import com.social.social_media.config.CustomUserDetails;
import com.social.social_media.config.JwtTokenProvider;
import com.social.social_media.dtos.*;
import com.social.social_media.exceptions.UserNotFoundException;
import com.social.social_media.models.Follow;
import com.social.social_media.models.User;
import com.social.social_media.repositories.UserRepository;
import com.social.social_media.service.CustomUserDetailsService;
import com.social.social_media.service.FollowService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowService followService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var encoder = new BCryptPasswordEncoder();
        var hashedPassword = encoder.encode(userRecordDto.password());

        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user, "password");
        user.setPassword(hashedPassword);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = customUserDetails.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setName(user.getName());
        userDTO.setDescription(user.getDescription());
        userDTO.setWebsite(user.getWebsite());
        userDTO.setSurname(user.getSurname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setSchool(user.getSchool());
        userDTO.setWork(user.getWork());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedAt(user.getCreatedAt());

        // Convert posts to PostDTOs
        List<PostDTO> postDTOs = user.getPosts().stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setDescription(post.getDescription());
            postDTO.setImgUrl(post.getImgUrl());
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setUpdateAt(post.getUpdateAt());
            return postDTO;
        }).collect(Collectors.toList());

        userDTO.setPosts(postDTOs);

        List<Follow> followers = followService.getFolloweeds(user);
        List<Follow> followings = followService.getAllFollowers(user);

        UserWithPostsAndFollowersDTO userWithPostsAndFollowersDTO = new UserWithPostsAndFollowersDTO();

        userWithPostsAndFollowersDTO.setUser(userDTO);
        userWithPostsAndFollowersDTO.setFollowers(followers.size());
        userWithPostsAndFollowersDTO.setFollowing(followings.size());

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new LoginResponse(token, userWithPostsAndFollowersDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> myProfile(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setName(user.getName());
        userDTO.setDescription(user.getDescription());
        userDTO.setWebsite(user.getWebsite());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setSchool(user.getSchool());
        userDTO.setWork(user.getWork());
        userDTO.setCreatedAt(user.getCreatedAt());

        // Convert posts to PostDTOs
        List<PostDTO> postDTOs = user.getPosts().stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setDescription(post.getDescription());
            postDTO.setImgUrl(post.getImgUrl());
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setUpdateAt(post.getUpdateAt());
            return postDTO;
        }).collect(Collectors.toList());

        userDTO.setPosts(postDTOs);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{username}/profile")
    public ResponseEntity<UserWithPostsAndFollowersDTO> profileFollowers(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserWithPostsAndFollowersDTO userWithPostsAndFollowersDTO = new UserWithPostsAndFollowersDTO();
            UserDTO userDTO = new UserDTO();
            userDTO.setIdUser(user.getIdUser());
            userDTO.setName(user.getName());
            userDTO.setDescription(user.getDescription());
            userDTO.setWebsite(user.getWebsite());
            userDTO.setSurname(user.getSurname());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setCity(user.getCity());
            userDTO.setSchool(user.getSchool());
            userDTO.setWork(user.getWork());
            userDTO.setCreatedAt(user.getCreatedAt());

            // Convert posts to PostDTOs
            List<PostDTO> postDTOs = user.getPosts().stream().map(post -> {
                PostDTO postDTO = new PostDTO();
                postDTO.setIdPost(post.getIdPost());
                postDTO.setDescription(post.getDescription());
                postDTO.setImgUrl(post.getImgUrl());
                postDTO.setCreatedAt(post.getCreatedAt());
                postDTO.setUpdateAt(post.getUpdateAt());
                return postDTO;
            }).collect(Collectors.toList());

            userDTO.setPosts(postDTOs);

            List<Follow> followers = followService.getFolloweeds(user);
            List<Follow> followings = followService.getAllFollowers(user);

            userWithPostsAndFollowersDTO.setUser(userDTO);
            userWithPostsAndFollowersDTO.setFollowers(followers.size());
            userWithPostsAndFollowersDTO.setFollowing(followings.size());

            return ResponseEntity.ok(userWithPostsAndFollowersDTO);
        }
        return null;
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserWithPostsAndFollowersDTO> editUserProfile(@PathVariable UUID id, @RequestBody UserEditDTO userEditDTO) {
        User user = customUserDetailsService.updateUser(id, userEditDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setName(user.getName());
        userDTO.setDescription(user.getDescription());
        userDTO.setWebsite(user.getWebsite());
        userDTO.setSurname(user.getSurname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setSchool(user.getSchool());
        userDTO.setWork(user.getWork());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedAt(user.getCreatedAt());

        // Convert posts to PostDTOs
        List<PostDTO> postDTOs = user.getPosts().stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setDescription(post.getDescription());
            postDTO.setImgUrl(post.getImgUrl());
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setUpdateAt(post.getUpdateAt());
            return postDTO;
        }).collect(Collectors.toList());

        userDTO.setPosts(postDTOs);

        List<Follow> followers = followService.getFolloweeds(user);
        List<Follow> followings = followService.getAllFollowers(user);

        UserWithPostsAndFollowersDTO userWithPostsAndFollowersDTO = new UserWithPostsAndFollowersDTO();

        userWithPostsAndFollowersDTO.setUser(userDTO);
        userWithPostsAndFollowersDTO.setFollowers(followers.size());
        userWithPostsAndFollowersDTO.setFollowing(followings.size());

        return ResponseEntity.ok(userWithPostsAndFollowersDTO);
    }
}