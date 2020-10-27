package com.brainyodds.service;

import com.brainyodds.service.dto.LeagueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.brainyodds.domain.League}.
 */
public interface LeagueService {

    /**
     * Save a league.
     *
     * @param leagueDTO the entity to save.
     * @return the persisted entity.
     */
    LeagueDTO save(LeagueDTO leagueDTO);

    /**
     * Get all the leagues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LeagueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" league.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LeagueDTO> findOne(Long id);

    /**
     * Delete the "id" league.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
