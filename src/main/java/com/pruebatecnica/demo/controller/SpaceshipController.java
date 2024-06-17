package com.pruebatecnica.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pruebatecnica.demo.model.Spaceship;
import com.pruebatecnica.demo.service.SpaceshipService;
import java.util.List;

@RestController
@RequestMapping("/spaceships")
@Tag(name = "Spaceship Management System", description = "Operations pertaining to spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    @GetMapping
    @Operation(summary = "Get all spaceships")
    public List<Spaceship> getAllSpaceships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return spaceshipService.getAllSpaceships(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a spaceship by Id")
    public Spaceship getSpaceshipById(@PathVariable Long id) {
        return spaceshipService.getSpaceshipById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search spaceships by name")
    public List<Spaceship> searchSpaceships(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return spaceshipService.searchSpaceshipsByName(name, page, size);
    }

    @PostMapping
    @Operation(summary = "Add a new spaceship")
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return spaceshipService.createSpaceship(spaceship);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing spaceship")
    public Spaceship updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        return spaceshipService.updateSpaceship(id, spaceship);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a spaceship")
    public void deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
    }
}
