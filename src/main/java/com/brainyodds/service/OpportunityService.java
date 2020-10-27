package com.brainyodds.service;

import com.brainyodds.service.dto.OpportunityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.brainyodds.domain.Opportunity}.
 */
public interface OpportunityService {

    /**
     * Save a opportunity.
     *
     * @param opportunityDTO the entity to save.
     * @return the persisted entity.
     */
    OpportunityDTO save(OpportunityDTO opportunityDTO);

    /**
     * Get all the opportunities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OpportunityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" opportunity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OpportunityDTO> findOne(Long id);

    /**
     * Delete the "id" opportunity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
