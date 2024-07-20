package com.social.social_media.service;

import com.social.social_media.dtos.StorieRecordDTO;
import com.social.social_media.dtos.StorieUpdateRecordDTO;
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

    @Transactional
    public Storie createStorie(StorieRecordDTO storieRecordDTO) {
        var user = userRepository.findById(storieRecordDTO.userId());
        if (user.isEmpty()) {
            return null;
        }
        var storie = new Storie(storieRecordDTO.content(), user.get(), storieRecordDTO.expiresAt());
        return storieRepository.save(storie);
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
