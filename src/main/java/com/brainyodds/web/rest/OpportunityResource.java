package com.brainyodds.web.rest;

import com.brainyodds.domain.Opportunity;
import com.brainyodds.service.OpportunityService;
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
 * REST controller for managing {@link com.brainyodds.domain.Opportunity}.
 */
@RestController
@RequestMapping("/api")
public class OpportunityResource {

    private final Logger log = LoggerFactory.getLogger(OpportunityResource.class);

    private static final String ENTITY_NAME = "opportunity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpportunityService opportunityService;

    public OpportunityResource(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    /**
     * {@code POST  /opportunities} : Create a new opportunity.
     *
     * @param opportunity the opportunity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opportunity, or with status {@code 400 (Bad Request)} if the opportunity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opportunities")
    public ResponseEntity<Opportunity> createOpportunity(@RequestBody Opportunity opportunity) throws URISyntaxException {
        log.debug("REST request to save Opportunity : {}", opportunity);
        if (opportunity.getId() != null) {
            throw new BadRequestAlertException("A new opportunity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Opportunity result = opportunityService.save(opportunity);
        return ResponseEntity.created(new URI("/api/opportunities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opportunities} : Updates an existing opportunity.
     *
     * @param opportunity the opportunity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opportunity,
     * or with status {@code 400 (Bad Request)} if the opportunity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opportunity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opportunities")
    public ResponseEntity<Opportunity> updateOpportunity(@RequestBody Opportunity opportunity) throws URISyntaxException {
        log.debug("REST request to update Opportunity : {}", opportunity);
        if (opportunity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Opportunity result = opportunityService.save(opportunity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opportunity.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /opportunities} : get all the opportunities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of opportunities in body.
     */
    @GetMapping("/opportunities")
    public List<Opportunity> getAllOpportunities() {
        log.debug("REST request to get all Opportunities");
        return opportunityService.findAll();
    }

    /**
     * {@code GET  /opportunities/:id} : get the "id" opportunity.
     *
     * @param id the id of the opportunity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opportunity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opportunities/{id}")
    public ResponseEntity<Opportunity> getOpportunity(@PathVariable Long id) {
        log.debug("REST request to get Opportunity : {}", id);
        Optional<Opportunity> opportunity = opportunityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opportunity);
    }

    /**
     * {@code DELETE  /opportunities/:id} : delete the "id" opportunity.
     *
     * @param id the id of the opportunity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opportunities/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        log.debug("REST request to delete Opportunity : {}", id);
        opportunityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
