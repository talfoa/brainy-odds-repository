package com.brainyodds.service.impl;

import com.brainyodds.service.GameEventService;
import com.brainyodds.domain.GameEvent;
import com.brainyodds.repository.GameEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GameEvent}.
 */
@Service
@Transactional
public class GameEventServiceImpl implements GameEventService {

    private final Logger log = LoggerFactory.getLogger(GameEventServiceImpl.class);

    private final GameEventRepository gameEventRepository;

    public GameEventServiceImpl(GameEventRepository gameEventRepository) {
        this.gameEventRepository = gameEventRepository;
    }

    @Override
    public GameEvent save(GameEvent gameEvent) {
        log.debug("Request to save GameEvent : {}", gameEvent);
        return gameEventRepository.save(gameEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameEvent> findAll() {
        log.debug("Request to get all GameEvents");
        return gameEventRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GameEvent> findOne(Long id) {
        log.debug("Request to get GameEvent : {}", id);
        return gameEventRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GameEvent : {}", id);
        gameEventRepository.deleteById(id);
    }
}
