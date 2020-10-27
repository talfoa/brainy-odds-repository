package com.brainyodds.service;

import com.brainyodds.service.dto.SportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.brainyodds.domain.Sport}.
 */
public interface SportService {

    /**
     * Save a sport.
     *
     * @param sportDTO the entity to save.
     * @return the persisted entity.
     */
    SportDTO save(SportDTO sportDTO);

    /**
     * Get all the sports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SportDTO> findOne(Long id);

    /**
     * Delete the "id" sport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
