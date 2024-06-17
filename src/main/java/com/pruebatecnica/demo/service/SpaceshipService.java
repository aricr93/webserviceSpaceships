package com.pruebatecnica.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pruebatecnica.demo.exception.ResourceNotFoundException;
import com.pruebatecnica.demo.model.Spaceship;
import com.pruebatecnica.demo.repository.SpaceshipRepository;

import java.util.List;

@Service
public class SpaceshipService {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    private static final Logger logger = LoggerFactory.getLogger(SpaceshipService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String SWAPI_BASE_URL = "https://swapi.dev/api/starships/?format=json";

    public List<Spaceship> getAllSpaceships(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return spaceshipRepository.findAll(pageable).getContent();
    }

    @Cacheable(value = "spaceships", key = "#id")
    public Spaceship getSpaceshipById(Long id) {
        logger.info("Fetching spaceship with id: {}", id);
        return spaceshipRepository.findById(id).orElseThrow(() -> {
            logger.error("Spaceship not found with id: {}", id);
            return new ResourceNotFoundException("Spaceship not found with id: " + id);
        });
    }

    public List<Spaceship> searchSpaceshipsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return spaceshipRepository.findByNameContaining(name, pageable).getContent();
    }

    @Cacheable("spaceships")
    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    @CacheEvict(value = "spaceships", key = "#spaceship.id")
    public Spaceship updateSpaceship(Long id, Spaceship spaceship) {
        Spaceship existingSpaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship not found with id: " + id));
        existingSpaceship.setName(spaceship.getName());
        existingSpaceship.setModel(spaceship.getModel());
        existingSpaceship.setManufacturer(spaceship.getManufacturer());
        existingSpaceship.setCostInCredits(spaceship.getCostInCredits());
        existingSpaceship.setLength(spaceship.getLength());
        existingSpaceship.setMaxAtmospheringSpeed(spaceship.getMaxAtmospheringSpeed());
        existingSpaceship.setCrew(spaceship.getCrew());
        existingSpaceship.setPassengers(spaceship.getPassengers());
        existingSpaceship.setCargoCapacity(spaceship.getCargoCapacity());
        existingSpaceship.setConsumables(spaceship.getConsumables());
        existingSpaceship.setHyperdriveRating(spaceship.getHyperdriveRating());
        existingSpaceship.setMglt(spaceship.getMglt());
        existingSpaceship.setStarshipClass(spaceship.getStarshipClass());
        existingSpaceship.setFilms(spaceship.getFilms());
        existingSpaceship.setCreated(spaceship.getCreated());
        existingSpaceship.setEdited(spaceship.getEdited());
        existingSpaceship.setUrl(spaceship.getUrl());
        return spaceshipRepository.save(existingSpaceship);
    }

    public void deleteSpaceship(Long id) {
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spaceship not found with id: " + id));
        spaceshipRepository.delete(spaceship);
    }

    public void fetchSpaceshipsFromSwapi() {
        try {
            SwapiResponse response = restTemplate.getForObject(SWAPI_BASE_URL, SwapiResponse.class);
            if (response != null && response.getResults() != null) {
                for (SwapiSpaceship swapiSpaceship : response.getResults()) {
                    Spaceship spaceship = new Spaceship();
                    spaceship.setName(swapiSpaceship.getName());
                    spaceship.setModel(swapiSpaceship.getModel());
                    spaceship.setManufacturer(swapiSpaceship.getManufacturer());
                    spaceship.setCostInCredits(swapiSpaceship.getCost_in_credits());
                    spaceship.setLength(swapiSpaceship.getLength());
                    spaceship.setMaxAtmospheringSpeed(swapiSpaceship.getMax_atmosphering_speed());
                    spaceship.setCrew(swapiSpaceship.getCrew());
                    spaceship.setPassengers(swapiSpaceship.getPassengers());
                    spaceship.setCargoCapacity(swapiSpaceship.getCargo_capacity());
                    spaceship.setConsumables(swapiSpaceship.getConsumables());
                    spaceship.setHyperdriveRating(swapiSpaceship.getHyperdrive_rating());
                    spaceship.setMglt(swapiSpaceship.getMGLT());
                    spaceship.setStarshipClass(swapiSpaceship.getStarship_class());
                    spaceship.setFilms(swapiSpaceship.getFilms());
                    spaceship.setCreated(swapiSpaceship.getCreated());
                    spaceship.setEdited(swapiSpaceship.getEdited());
                    spaceship.setUrl(swapiSpaceship.getUrl());
                    spaceshipRepository.save(spaceship);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
