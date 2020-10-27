package com.brainyodds.service.impl;

import com.brainyodds.service.GameEventService;
import com.brainyodds.domain.GameEvent;
import com.brainyodds.repository.GameEventRepository;
import com.brainyodds.service.dto.GameEventDTO;
import com.brainyodds.service.mapper.GameEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GameEvent}.
 */
@Service
@Transactional
public class GameEventServiceImpl implements GameEventService {

    private final Logger log = LoggerFactory.getLogger(GameEventServiceImpl.class);

    private final GameEventRepository gameEventRepository;

    private final GameEventMapper gameEventMapper;

    public GameEventServiceImpl(GameEventRepository gameEventRepository, GameEventMapper gameEventMapper) {
        this.gameEventRepository = gameEventRepository;
        this.gameEventMapper = gameEventMapper;
    }

    @Override
    public GameEventDTO save(GameEventDTO gameEventDTO) {
        log.debug("Request to save GameEvent : {}", gameEventDTO);
        GameEvent gameEvent = gameEventMapper.toEntity(gameEventDTO);
        gameEvent = gameEventRepository.save(gameEvent);
        return gameEventMapper.toDto(gameEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GameEvents");
        return gameEventRepository.findAll(pageable)
            .map(gameEventMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GameEventDTO> findOne(Long id) {
        log.debug("Request to get GameEvent : {}", id);
        return gameEventRepository.findById(id)
            .map(gameEventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GameEvent : {}", id);
        gameEventRepository.deleteById(id);
    }
}
