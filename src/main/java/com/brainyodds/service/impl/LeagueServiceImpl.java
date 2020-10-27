package com.brainyodds.service.impl;

import com.brainyodds.service.LeagueService;
import com.brainyodds.domain.League;
import com.brainyodds.repository.LeagueRepository;
import com.brainyodds.service.dto.LeagueDTO;
import com.brainyodds.service.mapper.LeagueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link League}.
 */
@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {

    private final Logger log = LoggerFactory.getLogger(LeagueServiceImpl.class);

    private final LeagueRepository leagueRepository;

    private final LeagueMapper leagueMapper;

    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueMapper leagueMapper) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
    }

    @Override
    public LeagueDTO save(LeagueDTO leagueDTO) {
        log.debug("Request to save League : {}", leagueDTO);
        League league = leagueMapper.toEntity(leagueDTO);
        league = leagueRepository.save(league);
        return leagueMapper.toDto(league);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeagueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Leagues");
        return leagueRepository.findAll(pageable)
            .map(leagueMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LeagueDTO> findOne(Long id) {
        log.debug("Request to get League : {}", id);
        return leagueRepository.findById(id)
            .map(leagueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete League : {}", id);
        leagueRepository.deleteById(id);
    }
}
