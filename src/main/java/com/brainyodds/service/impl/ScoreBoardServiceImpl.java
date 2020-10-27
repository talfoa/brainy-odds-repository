package com.brainyodds.service.impl;

import com.brainyodds.service.ScoreBoardService;
import com.brainyodds.domain.ScoreBoard;
import com.brainyodds.repository.ScoreBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ScoreBoard}.
 */
@Service
@Transactional
public class ScoreBoardServiceImpl implements ScoreBoardService {

    private final Logger log = LoggerFactory.getLogger(ScoreBoardServiceImpl.class);

    private final ScoreBoardRepository scoreBoardRepository;

    public ScoreBoardServiceImpl(ScoreBoardRepository scoreBoardRepository) {
        this.scoreBoardRepository = scoreBoardRepository;
    }

    @Override
    public ScoreBoard save(ScoreBoard scoreBoard) {
        log.debug("Request to save ScoreBoard : {}", scoreBoard);
        return scoreBoardRepository.save(scoreBoard);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScoreBoard> findAll() {
        log.debug("Request to get all ScoreBoards");
        return scoreBoardRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ScoreBoard> findOne(Long id) {
        log.debug("Request to get ScoreBoard : {}", id);
        return scoreBoardRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ScoreBoard : {}", id);
        scoreBoardRepository.deleteById(id);
    }
}
