package com.social.social_media.controllers;

import com.social.social_media.dtos.BlockRecordDTO;
import com.social.social_media.models.Block;
import com.social.social_media.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BlockController {
    @Autowired
    private BlockService blockService;

    @GetMapping
    public ResponseEntity<List<Block>> getAllBlocks() {
        List<Block> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Block> getBlockById(@PathVariable UUID id) {
        Block block = blockService.getBlockById(id);
        if (block != null) {
            return ResponseEntity.ok(block);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Block> createBlock(BlockRecordDTO blockRecordDTO) {
        Block newBlock = blockService.createBlock(blockRecordDTO.blockerId(), blockRecordDTO.blockedId());
        if (newBlock != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newBlock);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Block> updateBlock(@PathVariable UUID id, @RequestBody Block updatedBlock) {
        Block block = blockService.updateBlock(id, updatedBlock);
        if (block != null) {
            return ResponseEntity.ok(block);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable UUID id) {
        blockService.deleteBlock(id);
        return ResponseEntity.noContent().build();
    }
}
