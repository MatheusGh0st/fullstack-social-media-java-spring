package com.social.social_media.controllers;

import com.social.social_media.dtos.StorieRecordDTO;
import com.social.social_media.models.Storie;
import com.social.social_media.service.StorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Storie> createStorie(@RequestBody StorieRecordDTO storieRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storieService.createStorie(storieRecordDTO));
    }

    @PutMapping("/storie/{id}")
    public ResponseEntity<Storie> updateStorie(@PathVariable UUID id, @RequestBody StorieRecordDTO storieRecordDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(storieService.updateStorie(storieRecordDTO));
    }

    @DeleteMapping("/storie/{id}")
    public ResponseEntity<String> deleteStorie(@PathVariable UUID id) {
        storieService.deleteStorie(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete storie succesfully");
    }
}