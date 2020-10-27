package com.brainyodds.web.rest;

import com.brainyodds.domain.GameEvent;
import com.brainyodds.service.GameEventService;
import com.brainyodds.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.brainyodds.domain.GameEvent}.
 */
@RestController
@RequestMapping("/api")
public class GameEventResource {

    private final Logger log = LoggerFactory.getLogger(GameEventResource.class);

    private static final String ENTITY_NAME = "gameEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GameEventService gameEventService;

    public GameEventResource(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    /**
     * {@code POST  /game-events} : Create a new gameEvent.
     *
     * @param gameEvent the gameEvent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gameEvent, or with status {@code 400 (Bad Request)} if the gameEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/game-events")
    public ResponseEntity<GameEvent> createGameEvent(@RequestBody GameEvent gameEvent) throws URISyntaxException {
        log.debug("REST request to save GameEvent : {}", gameEvent);
        if (gameEvent.getId() != null) {
            throw new BadRequestAlertException("A new gameEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GameEvent result = gameEventService.save(gameEvent);
        return ResponseEntity.created(new URI("/api/game-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /game-events} : Updates an existing gameEvent.
     *
     * @param gameEvent the gameEvent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gameEvent,
     * or with status {@code 400 (Bad Request)} if the gameEvent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gameEvent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/game-events")
    public ResponseEntity<GameEvent> updateGameEvent(@RequestBody GameEvent gameEvent) throws URISyntaxException {
        log.debug("REST request to update GameEvent : {}", gameEvent);
        if (gameEvent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GameEvent result = gameEventService.save(gameEvent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gameEvent.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /game-events} : get all the gameEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gameEvents in body.
     */
    @GetMapping("/game-events")
    public List<GameEvent> getAllGameEvents() {
        log.debug("REST request to get all GameEvents");
        return gameEventService.findAll();
    }

    /**
     * {@code GET  /game-events/:id} : get the "id" gameEvent.
     *
     * @param id the id of the gameEvent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gameEvent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/game-events/{id}")
    public ResponseEntity<GameEvent> getGameEvent(@PathVariable Long id) {
        log.debug("REST request to get GameEvent : {}", id);
        Optional<GameEvent> gameEvent = gameEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gameEvent);
    }

    /**
     * {@code DELETE  /game-events/:id} : delete the "id" gameEvent.
     *
     * @param id the id of the gameEvent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/game-events/{id}")
    public ResponseEntity<Void> deleteGameEvent(@PathVariable Long id) {
        log.debug("REST request to delete GameEvent : {}", id);
        gameEventService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
