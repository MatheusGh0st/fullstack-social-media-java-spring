package com.social.social_media.service;

import com.social.social_media.dtos.BlockDTO;
import com.social.social_media.dtos.BlockRecordDTO;
import com.social.social_media.dtos.FollowResponseDTO;
import com.social.social_media.dtos.IsBlockedDTO;
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

    public IsBlockedDTO isBlocked(BlockRecordDTO blockRecordDTO) {
        if (blockRecordDTO.blockerId().equals(blockRecordDTO.blockedId())) {
            return new IsBlockedDTO(null, false);
        }
        Optional<User> userBlocked = userRepository.findById(blockRecordDTO.blockedId());
        Optional<User> userBlocker = userRepository.findById(blockRecordDTO.blockerId());

        var blockId = blockRepository.findByBlockerIdAndBlockedId(userBlocker.get(), userBlocked.get()).orElse(null);

        boolean isBlocked = blockRepository.existsByBlockerAndBlocked(userBlocker.get(), userBlocked.get());

        if (blockId != null) {
            return new IsBlockedDTO(blockId.getIdBlock(), isBlocked);
        }

        return new IsBlockedDTO(null, isBlocked);
    }

    public Block createBlock(UUID blockerId, UUID blockedId) {
        Optional<User> blocker = userRepository.findById(blockerId);
        Optional<User> blocked = userRepository.findById(blockedId);

        if (blocker.isPresent() && blocked.isPresent()) {
            Optional<Block> existingBlock = blockRepository.findByBlockerIdAndBlockedId(blocker.get(), blocked.get());

            if (existingBlock.isPresent()) {
                System.out.println("Block relationship alerady exists between the users");
                return null;
            };
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
