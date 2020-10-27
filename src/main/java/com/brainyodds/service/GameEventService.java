package com.brainyodds.service;

import com.brainyodds.domain.GameEvent;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GameEvent}.
 */
public interface GameEventService {

    /**
     * Save a gameEvent.
     *
     * @param gameEvent the entity to save.
     * @return the persisted entity.
     */
    GameEvent save(GameEvent gameEvent);

    /**
     * Get all the gameEvents.
     *
     * @return the list of entities.
     */
    List<GameEvent> findAll();


    /**
     * Get the "id" gameEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GameEvent> findOne(Long id);

    /**
     * Delete the "id" gameEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
