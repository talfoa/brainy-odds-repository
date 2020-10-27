package com.brainyodds.service.mapper;


import com.brainyodds.domain.*;
import com.brainyodds.service.dto.OpportunityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Opportunity} and its DTO {@link OpportunityDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface OpportunityMapper extends EntityMapper<OpportunityDTO, Opportunity> {

    @Mapping(source = "match.id", target = "matchId")
    OpportunityDTO toDto(Opportunity opportunity);

    @Mapping(source = "matchId", target = "match")
    Opportunity toEntity(OpportunityDTO opportunityDTO);

    default Opportunity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Opportunity opportunity = new Opportunity();
        opportunity.setId(id);
        return opportunity;
    }
}
