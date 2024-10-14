package com.social.social_media.controllers;

import com.social.social_media.dtos.*;
import com.social.social_media.models.Storie;
import com.social.social_media.service.StorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
public class StorieController {
    @Autowired
    StorieService storieService;

    @GetMapping("/stories")
    public ResponseEntity<List<Storie>> getAllStories() {
        return ResponseEntity.status(HttpStatus.OK).body(storieService.getAllStories());
    }

    @PostMapping("/storie")
    public ResponseEntity<StorieResponseDTO> createStorie(@RequestBody StorieRequestDTO storieRequestDTO) {
        StorieRecordDTO storieRecordDTO = new StorieRecordDTO(storieRequestDTO.userId(), storieRequestDTO.content(), convertToLocalDateTime(storieRequestDTO.expiresAt()));
        return ResponseEntity.status(HttpStatus.CREATED).body(storieService.createStorie(storieRecordDTO));
    }

    @GetMapping("/storiesEx")
    public ResponseEntity<List<StorieWithUserDTO>> getAllStoriesExpiresAtUser(
            @ModelAttribute StorieGetByDateOrUserStr params)
    {
        LocalDateTime dateTime = convertToLocalDateTime(params.dateTime());
        StorieGetByDateOrUser parameters = new StorieGetByDateOrUser(dateTime, params.userId());
        List<StorieWithUserDTO> stories = storieService.getStoriesByDateOrUser(parameters);

        return ResponseEntity.ok(stories);
    }

    private LocalDateTime convertToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    @PutMapping("/storie/{id}")
    public ResponseEntity<Storie> updateStorie(@PathVariable UUID id, @RequestBody StorieUpdateRecordDTO storieUpdateRecordDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(storieService.updateStorie(id, storieUpdateRecordDTO));
    }

    @DeleteMapping("/storie/{id}")
    public ResponseEntity<String> deleteStorie(@PathVariable UUID id) {
        storieService.deleteStorie(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete storie succesfully");
    }
}