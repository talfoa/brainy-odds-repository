package com.brainyodds.service.impl;

import com.brainyodds.service.ScoreBoardService;
import com.brainyodds.domain.ScoreBoard;
import com.brainyodds.repository.ScoreBoardRepository;
import com.brainyodds.service.dto.ScoreBoardDTO;
import com.brainyodds.service.mapper.ScoreBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ScoreBoard}.
 */
@Service
@Transactional
public class ScoreBoardServiceImpl implements ScoreBoardService {

    private final Logger log = LoggerFactory.getLogger(ScoreBoardServiceImpl.class);

    private final ScoreBoardRepository scoreBoardRepository;

    private final ScoreBoardMapper scoreBoardMapper;

    public ScoreBoardServiceImpl(ScoreBoardRepository scoreBoardRepository, ScoreBoardMapper scoreBoardMapper) {
        this.scoreBoardRepository = scoreBoardRepository;
        this.scoreBoardMapper = scoreBoardMapper;
    }

    @Override
    public ScoreBoardDTO save(ScoreBoardDTO scoreBoardDTO) {
        log.debug("Request to save ScoreBoard : {}", scoreBoardDTO);
        ScoreBoard scoreBoard = scoreBoardMapper.toEntity(scoreBoardDTO);
        scoreBoard = scoreBoardRepository.save(scoreBoard);
        return scoreBoardMapper.toDto(scoreBoard);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ScoreBoardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ScoreBoards");
        return scoreBoardRepository.findAll(pageable)
            .map(scoreBoardMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ScoreBoardDTO> findOne(Long id) {
        log.debug("Request to get ScoreBoard : {}", id);
        return scoreBoardRepository.findById(id)
            .map(scoreBoardMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ScoreBoard : {}", id);
        scoreBoardRepository.deleteById(id);
    }
}
