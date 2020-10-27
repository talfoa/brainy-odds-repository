package com.brainyodds.service.impl;

import com.brainyodds.service.LeagueService;
import com.brainyodds.domain.League;
import com.brainyodds.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link League}.
 */
@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

    private final Logger log = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public League save(League league) {
        log.debug("Request to save League : {}", league);
        return leagueRepository.save(league);
    }

    @Override
    @Transactional(readOnly = true)
    public List<League> findAll() {
        log.debug("Request to get all Leagues");
        return leagueRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<League> findOne(Long id) {
        log.debug("Request to get League : {}", id);
        return leagueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete League : {}", id);
        leagueRepository.deleteById(id);
    }
}
