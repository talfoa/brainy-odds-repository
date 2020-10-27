package com.brainyodds.service;

import com.brainyodds.service.dto.ScoreBoardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.brainyodds.domain.ScoreBoard}.
 */
public interface ScoreBoardService {

    /**
     * Save a scoreBoard.
     *
     * @param scoreBoardDTO the entity to save.
     * @return the persisted entity.
     */
    ScoreBoardDTO save(ScoreBoardDTO scoreBoardDTO);

    /**
     * Get all the scoreBoards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ScoreBoardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" scoreBoard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ScoreBoardDTO> findOne(Long id);

    /**
     * Delete the "id" scoreBoard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
