package com.social.social_media.service;

import com.social.social_media.config.CustomUserDetails;
import com.social.social_media.dtos.PostUpdateRecordDto;
import com.social.social_media.dtos.UserEditDTO;
import com.social.social_media.models.Post;
import com.social.social_media.models.User;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new CustomUserDetails(user);
    }

    @Transactional
    public User updateUser(UUID id, UserEditDTO userEditDTO) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            String[] fieldNames = {"firstName", "surname", "description", "city", "school", "work", "website"};
            for (String fieldName : fieldNames) {
                switch (fieldName) {
                    case "firstName":
                        if (!userEditDTO.firstName().get().isEmpty()) {
                            user.get().setName(userEditDTO.firstName().get());
                        }
                        break;
                    case "surname":
                        if (!userEditDTO.surname().get().isEmpty()) {
                            user.get().setSurname(userEditDTO.surname().get());
                        }
                        break;
                    case "description":
                        if (!userEditDTO.description().get().isEmpty()) {
                            user.get().setDescription(userEditDTO.description().get());
                        }
                        break;
                    case "city":
                        if (!userEditDTO.city().get().isEmpty()) {
                            System.out.println();
                            user.get().setCity(userEditDTO.city().get());
                        }
                        break;
                    case "school":
                        if (!userEditDTO.school().get().isEmpty()) {
                            user.get().setSchool(userEditDTO.school().get());
                        }
                        break;
                    case "work":
                        if (!userEditDTO.work().get().isEmpty()) {
                            user.get().setWork(userEditDTO.work().get());
                        }
                        break;
                    case "website":
                        if (!userEditDTO.website().get().isEmpty()) {
                            user.get().setWebsite(userEditDTO.website().get());
                        }
                        break;
                }
            }
        }
        return userRepository.save(user.get());
    }
}