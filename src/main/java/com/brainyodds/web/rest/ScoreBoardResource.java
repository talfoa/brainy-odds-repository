package com.brainyodds.web.rest;

import com.brainyodds.service.ScoreBoardService;
import com.brainyodds.web.rest.errors.BadRequestAlertException;
import com.brainyodds.service.dto.ScoreBoardDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.brainyodds.domain.ScoreBoard}.
 */
@RestController
@RequestMapping("/api")
public class ScoreBoardResource {

    private final Logger log = LoggerFactory.getLogger(ScoreBoardResource.class);

    private static final String ENTITY_NAME = "scoreBoard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScoreBoardService scoreBoardService;

    public ScoreBoardResource(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    /**
     * {@code POST  /score-boards} : Create a new scoreBoard.
     *
     * @param scoreBoardDTO the scoreBoardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreBoardDTO, or with status {@code 400 (Bad Request)} if the scoreBoard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/score-boards")
    public ResponseEntity<ScoreBoardDTO> createScoreBoard(@RequestBody ScoreBoardDTO scoreBoardDTO) throws URISyntaxException {
        log.debug("REST request to save ScoreBoard : {}", scoreBoardDTO);
        if (scoreBoardDTO.getId() != null) {
            throw new BadRequestAlertException("A new scoreBoard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScoreBoardDTO result = scoreBoardService.save(scoreBoardDTO);
        return ResponseEntity.created(new URI("/api/score-boards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /score-boards} : Updates an existing scoreBoard.
     *
     * @param scoreBoardDTO the scoreBoardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreBoardDTO,
     * or with status {@code 400 (Bad Request)} if the scoreBoardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scoreBoardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/score-boards")
    public ResponseEntity<ScoreBoardDTO> updateScoreBoard(@RequestBody ScoreBoardDTO scoreBoardDTO) throws URISyntaxException {
        log.debug("REST request to update ScoreBoard : {}", scoreBoardDTO);
        if (scoreBoardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScoreBoardDTO result = scoreBoardService.save(scoreBoardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scoreBoardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /score-boards} : get all the scoreBoards.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scoreBoards in body.
     */
    @GetMapping("/score-boards")
    public ResponseEntity<List<ScoreBoardDTO>> getAllScoreBoards(Pageable pageable) {
        log.debug("REST request to get a page of ScoreBoards");
        Page<ScoreBoardDTO> page = scoreBoardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /score-boards/:id} : get the "id" scoreBoard.
     *
     * @param id the id of the scoreBoardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scoreBoardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/score-boards/{id}")
    public ResponseEntity<ScoreBoardDTO> getScoreBoard(@PathVariable Long id) {
        log.debug("REST request to get ScoreBoard : {}", id);
        Optional<ScoreBoardDTO> scoreBoardDTO = scoreBoardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scoreBoardDTO);
    }

    /**
     * {@code DELETE  /score-boards/:id} : delete the "id" scoreBoard.
     *
     * @param id the id of the scoreBoardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/score-boards/{id}")
    public ResponseEntity<Void> deleteScoreBoard(@PathVariable Long id) {
        log.debug("REST request to delete ScoreBoard : {}", id);
        scoreBoardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
