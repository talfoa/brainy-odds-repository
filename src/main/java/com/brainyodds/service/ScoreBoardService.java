package com.brainyodds.service;

import com.brainyodds.domain.ScoreBoard;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ScoreBoard}.
 */
public interface ScoreBoardService {

    /**
     * Save a scoreBoard.
     *
     * @param scoreBoard the entity to save.
     * @return the persisted entity.
     */
    ScoreBoard save(ScoreBoard scoreBoard);

    /**
     * Get all the scoreBoards.
     *
     * @return the list of entities.
     */
    List<ScoreBoard> findAll();


    /**
     * Get the "id" scoreBoard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ScoreBoard> findOne(Long id);

    /**
     * Delete the "id" scoreBoard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
