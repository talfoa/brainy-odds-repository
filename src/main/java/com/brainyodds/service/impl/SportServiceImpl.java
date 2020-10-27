package com.brainyodds.service.impl;

import com.brainyodds.service.SportService;
import com.brainyodds.domain.Sport;
import com.brainyodds.repository.SportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Sport}.
 */
@Service
@Transactional
public class SportServiceImpl implements SportService {

    private final Logger log = LoggerFactory.getLogger(SportServiceImpl.class);

    private final SportRepository sportRepository;

    public SportServiceImpl(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @Override
    public Sport save(Sport sport) {
        log.debug("Request to save Sport : {}", sport);
        return sportRepository.save(sport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sport> findAll() {
        log.debug("Request to get all Sports");
        return sportRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Sport> findOne(Long id) {
        log.debug("Request to get Sport : {}", id);
        return sportRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sport : {}", id);
        sportRepository.deleteById(id);
    }
}
