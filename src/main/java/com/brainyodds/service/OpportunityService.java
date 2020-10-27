package com.brainyodds.service;

import com.brainyodds.domain.Opportunity;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Opportunity}.
 */
public interface OpportunityService {

    /**
     * Save a opportunity.
     *
     * @param opportunity the entity to save.
     * @return the persisted entity.
     */
    Opportunity save(Opportunity opportunity);

    /**
     * Get all the opportunities.
     *
     * @return the list of entities.
     */
    List<Opportunity> findAll();


    /**
     * Get the "id" opportunity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Opportunity> findOne(Long id);

    /**
     * Delete the "id" opportunity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
