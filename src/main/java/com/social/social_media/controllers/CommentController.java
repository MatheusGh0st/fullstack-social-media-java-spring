package com.social.social_media.controllers;

import com.social.social_media.dtos.CommentRecordDTO;
import com.social.social_media.dtos.CommentResponseDTO;
import com.social.social_media.dtos.CommentUpdateRecordDTO;
import com.social.social_media.models.Comment;
import com.social.social_media.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(@PathVariable UUID postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsWithLikes(postId));
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody CommentRecordDTO commentRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(commentRecordDTO));
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable UUID id, CommentUpdateRecordDTO commentUpdateRecordDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.editComment(id, commentUpdateRecordDTO));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete comment Successfully");
    }
}
