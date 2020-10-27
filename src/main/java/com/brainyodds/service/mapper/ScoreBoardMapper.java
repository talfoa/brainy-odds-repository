package com.brainyodds.service.mapper;


import com.brainyodds.domain.*;
import com.brainyodds.service.dto.ScoreBoardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ScoreBoard} and its DTO {@link ScoreBoardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface ScoreBoardMapper extends EntityMapper<ScoreBoardDTO, ScoreBoard> {

    @Mapping(source = "match.id", target = "matchId")
    ScoreBoardDTO toDto(ScoreBoard scoreBoard);

    @Mapping(source = "matchId", target = "match")
    ScoreBoard toEntity(ScoreBoardDTO scoreBoardDTO);

    default ScoreBoard fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setId(id);
        return scoreBoard;
    }
}
