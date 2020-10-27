package com.brainyodds.service.impl;

import com.brainyodds.service.OpportunityService;
import com.brainyodds.domain.Opportunity;
import com.brainyodds.repository.OpportunityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Opportunity}.
 */
@Service
@Transactional
public class OpportunityServiceImpl implements OpportunityService {

    private final Logger log = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    private final OpportunityRepository opportunityRepository;

    public OpportunityServiceImpl(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public Opportunity save(Opportunity opportunity) {
        log.debug("Request to save Opportunity : {}", opportunity);
        return opportunityRepository.save(opportunity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Opportunity> findAll() {
        log.debug("Request to get all Opportunities");
        return opportunityRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Opportunity> findOne(Long id) {
        log.debug("Request to get Opportunity : {}", id);
        return opportunityRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Opportunity : {}", id);
        opportunityRepository.deleteById(id);
    }
}
