package com.social.social_media.controllers;

import com.social.social_media.config.CustomUserDetails;
import com.social.social_media.config.JwtTokenProvider;
import com.social.social_media.dtos.*;
import com.social.social_media.exceptions.UserNotFoundException;
import com.social.social_media.models.User;
import com.social.social_media.repositories.UserRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserRepository userRepository;

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
        userDTO.setSurname(user.getSurname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setSchool(user.getSchool());
        userDTO.setWork(user.getWork());
        userDTO.setUsername(user.getUsername());

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new LoginResponse(token, userDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> myProfile(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setSchool(user.getSchool());
        userDTO.setWork(user.getWork());

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

    @GetMapping("/{username}/followers")
    public ResponseEntity<UserWithPostsAndFollowersDTO> profileFollowers(@PathVariable String username) {
        Optional<UserWithPostsAndFollowersDTO> userOptional = userRepository.findUserWithPostsAndFollowers(username).stream().findFirst();

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if user is not found
        }

        UserWithPostsAndFollowersDTO userWithPostsAndFollowersDTO = userOptional.get(); // Get the user directly

        return ResponseEntity.ok(userWithPostsAndFollowersDTO); // Return the found user
    }
}