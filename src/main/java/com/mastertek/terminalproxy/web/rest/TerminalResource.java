package com.mastertek.terminalproxy.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mastertek.terminalproxy.domain.Terminal;

import com.mastertek.terminalproxy.repository.TerminalRepository;
import com.mastertek.terminalproxy.service.TerminalManager;
import com.mastertek.terminalproxy.service.WellcamDeviceService;
import com.mastertek.terminalproxy.web.rest.errors.BadRequestAlertException;
import com.mastertek.terminalproxy.web.rest.util.HeaderUtil;
import com.mastertek.terminalproxy.web.rest.vm.AddPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.DeletePersonVM;
import com.mastertek.terminalproxy.web.rest.vm.EditPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.QueryVM;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing Terminal.
 */
@RestController
@RequestMapping("/api")
public class TerminalResource {

    private final Logger log = LoggerFactory.getLogger(TerminalResource.class);

    private static final String ENTITY_NAME = "terminal";

    private final TerminalRepository terminalRepository;
    
    //TerminalManager terminalManager;
	
	private final WellcamDeviceService wellcamDeviceService;

    public TerminalResource(TerminalRepository terminalRepository,WellcamDeviceService wellcamDeviceService) {
        this.terminalRepository = terminalRepository;
        this.wellcamDeviceService = wellcamDeviceService;
    }

    /**
     * POST  /terminals : Create a new terminal.
     *
     * @param terminal the terminal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new terminal, or with status 400 (Bad Request) if the terminal has already an ID
     * @throws Exception 
     */
    @PostMapping("/terminals/addPerson")
    @Timed
    public ResponseEntity<Void> createTerminal(@Valid @RequestBody AddPersonVM addPersonVM) throws Exception {
        
       // Terminal result = terminalRepository.save(terminal);
    	wellcamDeviceService.addPerson(addPersonVM);
        return ResponseEntity.ok().build();
           
    }

    /**
     * PUT  /terminals : Updates an existing terminal.
     *
     * @param terminal the terminal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated terminal,
     * or with status 400 (Bad Request) if the terminal is not valid,
     * or with status 500 (Internal Server Error) if the terminal couldn't be updated
     * @throws Exception 
     */
    @PostMapping("/terminals/editPerson")
    @Timed
    public ResponseEntity<Terminal> updateTerminal(@Valid @RequestBody EditPersonVM editPersonVM) throws Exception {
       
        wellcamDeviceService.editPerson(editPersonVM);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /terminals/:id : delete the "id" terminal.
     *
     * @param id the id of the terminal to delete
     * @return the ResponseEntity with status 200 (OK)
     * @throws Exception 
     */
    @PostMapping("/terminals/deletePerson")
    @Timed
    public ResponseEntity<Void> deleteTerminal(@Valid @RequestBody DeletePersonVM deletePersonVM) throws Exception {
       
        wellcamDeviceService.deletePerson(deletePersonVM);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/terminals/query")
    @Timed
    public ResponseEntity<String> query(@Valid @RequestBody QueryVM queryVM) throws Exception {
       
        String result = wellcamDeviceService.query(queryVM);
        return ResponseEntity.ok().body(result);
    }
}
