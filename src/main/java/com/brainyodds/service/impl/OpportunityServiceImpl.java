package com.brainyodds.service.impl;

import com.brainyodds.service.OpportunityService;
import com.brainyodds.domain.Opportunity;
import com.brainyodds.repository.OpportunityRepository;
import com.brainyodds.service.dto.OpportunityDTO;
import com.brainyodds.service.mapper.OpportunityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Opportunity}.
 */
@Service
@Transactional
public class OpportunityServiceImpl implements OpportunityService {

    private final Logger log = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    private final OpportunityRepository opportunityRepository;

    private final OpportunityMapper opportunityMapper;

    public OpportunityServiceImpl(OpportunityRepository opportunityRepository, OpportunityMapper opportunityMapper) {
        this.opportunityRepository = opportunityRepository;
        this.opportunityMapper = opportunityMapper;
    }

    @Override
    public OpportunityDTO save(OpportunityDTO opportunityDTO) {
        log.debug("Request to save Opportunity : {}", opportunityDTO);
        Opportunity opportunity = opportunityMapper.toEntity(opportunityDTO);
        opportunity = opportunityRepository.save(opportunity);
        return opportunityMapper.toDto(opportunity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OpportunityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Opportunities");
        return opportunityRepository.findAll(pageable)
            .map(opportunityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OpportunityDTO> findOne(Long id) {
        log.debug("Request to get Opportunity : {}", id);
        return opportunityRepository.findById(id)
            .map(opportunityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Opportunity : {}", id);
        opportunityRepository.deleteById(id);
    }
}
