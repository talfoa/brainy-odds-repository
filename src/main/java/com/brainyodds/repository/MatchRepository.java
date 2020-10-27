package com.brainyodds.repository;

import com.brainyodds.domain.Match;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Match entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
