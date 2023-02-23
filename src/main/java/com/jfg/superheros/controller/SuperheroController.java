package com.jfg.superheros.controller;

import com.jfg.superheros.annotations.ExecutionTime;
import com.jfg.superheros.constants.ParamsUrl;
import com.jfg.superheros.constants.SuperheroUrls;
import com.jfg.superheros.model.entity.Superhero;
import com.jfg.superheros.service.SuperheroService;
import com.jfg.superheros.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(SuperheroUrls.REQUEST_MAPPING)
public class SuperheroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroController.class);
    private static final String SUPERHERO_DATA = "Superhero data";
    private static final String SUPERHEROS_DATA = "Superheros data";
    private static final String SUPERHERO_CREATED = "Superheros created";
    private static final String SUPERHERO_UPDATED = "Superheros updated";
    private static final String SUPERHERO_DELETED = "Superheros deleted";
    private final SuperheroService superheroService;

    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }


    /**
     * Get all superheros
     *
     * @return Set<Superhero>
     */
    @ExecutionTime
    @GetMapping(value = SuperheroUrls.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Superhero>> getAll() {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHEROS_DATA;
        Set<Superhero> content = null;

        try {
            content = superheroService.getAll();
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message, content);
    }


    /**
     * Get Superhero by id
     *
     * @param id Integer
     * @return Superhero
     */
    @ExecutionTime
    @GetMapping(value = SuperheroUrls.GET_BY_ID + ParamsUrl.ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> getById(@PathVariable("id") Integer id) throws InterruptedException {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHERO_DATA;
        Superhero content = null;

        try {
            content = superheroService.getById(id);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message, content);
    }


    /**
     * Get superheros by name content
     *
     * @param name String
     * @return Set<Superhero>
     */
    @ExecutionTime
    @GetMapping(value = SuperheroUrls.GET_BY_NAME_CONTENT + ParamsUrl.NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Superhero>> getByNameContent(@PathVariable("name") String name) {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHEROS_DATA;
        Set<Superhero> content = null;

        try {
            content = superheroService.getByNameContent(name);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message, content);
    }


    /**
     * Create superhero
     *
     * @param superhero Superhero
     * @return Superhero
     */
    @ExecutionTime
    @PostMapping(value = SuperheroUrls.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> create(@RequestBody Superhero superhero) {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHERO_CREATED;
        Superhero content = null;

        try {
            content = superheroService.create(superhero);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message, content);
    }


    /**
     * Update superhero
     *
     * @param superhero Superhero
     * @return Superhero
     */
    @ExecutionTime
    @PutMapping(value = SuperheroUrls.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> update(@RequestBody Superhero superhero) {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHERO_UPDATED;
        Superhero content = null;

        try {
            content = superheroService.update(superhero);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message, content);
    }


    /**
     * Delete superhero by id
     *
     * @param id Integer
     * @return Void
     */
    @ExecutionTime
    @DeleteMapping(value = SuperheroUrls.DELETE + ParamsUrl.ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        HttpStatus status = HttpStatus.OK;
        String message = SUPERHERO_DELETED;

        try {
            superheroService.delete(id);
        } catch (Exception ex) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
            LOGGER.error(ex.getMessage());
        }

        return ResponseUtils.createResponseEntity(status, message);
    }


}
