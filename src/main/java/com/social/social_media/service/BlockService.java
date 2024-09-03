package com.social.social_media.service;

import com.social.social_media.dtos.BlockDTO;
import com.social.social_media.models.Block;
import com.social.social_media.models.User;
import com.social.social_media.repositories.BlockRepository;
import com.social.social_media.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Block> getAllBlocks() { return blockRepository.findAll(); }

    public BlockDTO isBlocked(UUID id) {
        Optional<User> user = userRepository.findById(id);
        Optional<BlockDTO> block = blockRepository.isBlocked(user.get());
        return block.orElse(null);
    }

    public Block createBlock(UUID blockerId, UUID blockedId) {
        Optional<User> blocker = userRepository.findById(blockerId);
        Optional<User> blocked = userRepository.findById(blockedId);

        if (blocker.isPresent() && blocked.isPresent()) {
            Block newBlock = new Block(blocker.get(), blocked.get());
            return blockRepository.save(newBlock);
        }

        return null;
    }

    public Block updateBlock(UUID id, Block updateBlock) {
        Optional<Block> existingBlock = blockRepository.findById(id);

        if (existingBlock.isPresent()) {
            Block block = existingBlock.get();
            return blockRepository.save(block);
        }

        return null;
    }

    public void deleteBlock(UUID id) {
        blockRepository.deleteById(id);
    }
}
