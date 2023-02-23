package com.jfg.superheros.service;

import com.jfg.superheros.constants.GenericExceptionMessages;
import com.jfg.superheros.exception.AlreadyExistsException;
import com.jfg.superheros.exception.BadArgumentException;
import com.jfg.superheros.exception.ResourceNotFoundException;
import com.jfg.superheros.model.entity.Superhero;
import com.jfg.superheros.model.repository.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SuperheroService {
    private static final String SUPERHERO_NOT_FOUND = "Superhero not found";
    private static final String SUPERHERO_NAME_IS_REQUIRED = "Superhero name is required";
    private static final String SUPERHERO_ALREADY_EXISTS = "Superhero already exist";
    private final SuperheroRepository superheroRepository;

    public SuperheroService(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }


    /**
     * Get superhero by id
     *
     * @param id Integer
     * @return Superhero
     * @throws ResourceNotFoundException ResourceNotFoundException
     */
    public Superhero getById(Integer id) throws ResourceNotFoundException {
        Optional<Superhero> optionalSuperhero = superheroRepository.findById(id);

        if (optionalSuperhero.isEmpty()) throw new ResourceNotFoundException(SUPERHERO_NOT_FOUND);

        return optionalSuperhero.get();
    }


    /**
     * Get all superheros
     *
     * @return Set<Superhero>
     */
    public Set<Superhero> getAll() {
        return superheroRepository.findAll();
    }


    /**
     * Get by content name
     *
     * @param name String
     * @return Set<Superhero>
     */
    public Set<Superhero> getByNameContent(String name) {
        return superheroRepository.findByNameContent(name);
    }


    /**
     * Create superhero
     *
     * @param superhero Superhero
     * @throws BadArgumentException   BadArgumentException
     * @throws AlreadyExistsException AlreadyExistsException
     */
    public Superhero create(Superhero superhero) throws AlreadyExistsException, BadArgumentException {
        superhero.setId(null);
        this.validation(superhero);
        if (this.checkIfExistsByName(superhero.getName())) throw new AlreadyExistsException(SUPERHERO_ALREADY_EXISTS);

        return superheroRepository.save(superhero);
    }


    /**
     * Update superhero
     *
     * @param superhero Superhero
     * @return Superhero
     * @throws BadArgumentException   BadArgumentException
     * @throws AlreadyExistsException AlreadyExistsException
     */
    public Superhero update(Superhero superhero) throws AlreadyExistsException, BadArgumentException {
        if (superhero.getId() == null) throw new BadArgumentException(GenericExceptionMessages.ID_IS_REQUIRED);
        this.validation(superhero);
        if (this.checkIfExistsByNameDifferentId(superhero.getName(), superhero.getId()))
            throw new AlreadyExistsException(SUPERHERO_ALREADY_EXISTS);

        return superheroRepository.save(superhero);
    }


    public void delete(Integer id) throws AlreadyExistsException, BadArgumentException, ResourceNotFoundException {
        if (id == null) throw new BadArgumentException(GenericExceptionMessages.ID_IS_REQUIRED);
        Superhero superhero = getById(id);

        superheroRepository.deleteById(id);
    }


    /**
     * Validate superhero
     *
     * @param superhero Superhero
     * @throws BadArgumentException BadArgumentException
     */
    private void validation(Superhero superhero) throws BadArgumentException {
        if (superhero.getName() == null || superhero.getName().isEmpty())
            throw new BadArgumentException(SUPERHERO_NAME_IS_REQUIRED);
    }


    /**
     * Check if Superhero exists by name
     *
     * @param name String
     * @return exits Boolean
     */
    private Boolean checkIfExistsByName(String name) {
        return superheroRepository.findByNameIgnoreCase(name).isPresent();
    }


    /**
     * Check if Superhero exists by name different id
     *
     * @param name String
     * @param id   Integer
     * @return Boolean
     */
    private Boolean checkIfExistsByNameDifferentId(String name, Integer id) {
        return superheroRepository.findByNameContentDifferentId(name, id).isPresent();
    }
}
