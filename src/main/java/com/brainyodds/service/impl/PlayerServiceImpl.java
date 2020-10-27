package com.brainyodds.service.impl;

import com.brainyodds.service.PlayerService;
import com.brainyodds.domain.Player;
import com.brainyodds.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Player}.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(Player player) {
        log.debug("Request to save Player : {}", player);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        log.debug("Request to get all Players");
        return playerRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Player> findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        return playerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Player : {}", id);
        playerRepository.deleteById(id);
    }
}
