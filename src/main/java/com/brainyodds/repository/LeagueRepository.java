package com.brainyodds.repository;

import com.brainyodds.domain.League;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the League entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
}
