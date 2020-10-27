package com.brainyodds.service;

import com.brainyodds.domain.Sport;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Sport}.
 */
public interface SportService {

    /**
     * Save a sport.
     *
     * @param sport the entity to save.
     * @return the persisted entity.
     */
    Sport save(Sport sport);

    /**
     * Get all the sports.
     *
     * @return the list of entities.
     */
    List<Sport> findAll();


    /**
     * Get the "id" sport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Sport> findOne(Long id);

    /**
     * Delete the "id" sport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
