package com.brainyodds.service.mapper;


import com.brainyodds.domain.*;
import com.brainyodds.service.dto.SportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sport} and its DTO {@link SportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SportMapper extends EntityMapper<SportDTO, Sport> {


    @Mapping(target = "leagues", ignore = true)
    @Mapping(target = "removeLeague", ignore = true)
    @Mapping(target = "sports", ignore = true)
    @Mapping(target = "removeSport", ignore = true)
    Sport toEntity(SportDTO sportDTO);

    default Sport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sport sport = new Sport();
        sport.setId(id);
        return sport;
    }
}
