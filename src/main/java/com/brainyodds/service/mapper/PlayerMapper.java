package com.brainyodds.service.mapper;


import com.brainyodds.domain.*;
import com.brainyodds.service.dto.PlayerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Player} and its DTO {@link PlayerDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {

    @Mapping(source = "team.id", target = "teamId")
    PlayerDTO toDto(Player player);

    @Mapping(source = "teamId", target = "team")
    Player toEntity(PlayerDTO playerDTO);

    default Player fromId(Long id) {
        if (id == null) {
            return null;
        }
        Player player = new Player();
        player.setId(id);
        return player;
    }
}
