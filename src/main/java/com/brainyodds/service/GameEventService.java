package com.brainyodds.service;

import com.brainyodds.service.dto.GameEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.brainyodds.domain.GameEvent}.
 */
public interface GameEventService {

    /**
     * Save a gameEvent.
     *
     * @param gameEventDTO the entity to save.
     * @return the persisted entity.
     */
    GameEventDTO save(GameEventDTO gameEventDTO);

    /**
     * Get all the gameEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GameEventDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gameEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GameEventDTO> findOne(Long id);

    /**
     * Delete the "id" gameEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
