package com.social.social_media.service;

import com.social.social_media.dtos.*;
import com.social.social_media.models.Storie;
import com.social.social_media.repositories.StorieRepository;
import com.social.social_media.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StorieService {
    @Autowired
    private StorieRepository storieRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Storie> getAllStories() {
        return storieRepository.findAll();
    }

    public List<StorieWithUserDTO> getStoriesByDateOrUser(StorieGetByDateOrUser storieGetByDateOrUser) {
        var user = userRepository.findById(storieGetByDateOrUser.userId());
        List <StorieWithUserDTO> stories = storieRepository.findStoriesByDateOrUserId(storieGetByDateOrUser.dateTime(), user.get());
        if (stories.isEmpty()) {
            return null;
        }
        return stories;
    }

    @Transactional
    public StorieResponseDTO createStorie(StorieRecordDTO storieRecordDTO) {
        var user = userRepository.findById(storieRecordDTO.userId());
        if (user.isEmpty()) {
            return null;
        }
        var storie = new Storie(storieRecordDTO.content(), user.get(), storieRecordDTO.expiresAt());

        var storieSaved = storieRepository.save(storie);

        var userDTO = new UserDTO();
        var newStorie = new StorieResponseDTO();
        var userObj = storieSaved.getUser();

        newStorie.setIdStorie(storieSaved.getIdStorie());
        newStorie.setContent(storieSaved.getContent());
        userDTO.setIdUser(userObj.getIdUser());
        userDTO.setName(userObj.getName());
        userDTO.setDescription(userObj.getDescription());
        userDTO.setWebsite(userObj.getWebsite());
        userDTO.setSurname(userObj.getSurname());
        userDTO.setAvatar(userObj.getAvatar());
        userDTO.setEmail(userObj.getEmail());
        userDTO.setCity(userObj.getCity());
        userDTO.setSchool(userObj.getSchool());
        userDTO.setWork(userObj.getWork());
        userDTO.setUsername(userObj.getUsername());
        userDTO.setCreatedAt(userObj.getCreatedAt());
        newStorie.setUser(userDTO);
        newStorie.setExpiresAt(storieSaved.getExpiresAt());
        newStorie.setCreateAt(storieSaved.getCreateAt());

        return newStorie;
    }

    @Transactional
    public Storie updateStorie(UUID id, StorieUpdateRecordDTO storieUpdateRecordDTO) {
        var storie = storieRepository.findById(id);
        if (storie.isEmpty()) {
            return null;
        }
        storie.get().setContent(storieUpdateRecordDTO.content());
        return storieRepository.save(storie.get());
    }

    @Transactional
    public void deleteStorie(UUID id) {
        storieRepository.deleteById(id);
    }
}
