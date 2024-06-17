package com.pruebatecnica.demo;

import com.pruebatecnica.demo.exception.ResourceNotFoundException;
import com.pruebatecnica.demo.model.Spaceship;
import com.pruebatecnica.demo.repository.SpaceshipRepository;
import com.pruebatecnica.demo.service.SpaceshipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpaceshipServiceTests {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @Test
    public void testGetSpaceshipById() {
        Long id = 1L;
        Spaceship spaceship = new Spaceship();
        spaceship.setId(id);
        spaceship.setName("Millennium Falcon");
        when(spaceshipRepository.findById(id)).thenReturn(Optional.of(spaceship));
        Spaceship result = spaceshipService.getSpaceshipById(id);
        assertEquals(id, result.getId());
        assertEquals("Millennium Falcon", result.getName());
        verify(spaceshipRepository, times(1)).findById(id);
    }

    @Test
    public void testGetSpaceshipByIdNotFound() {
        Long id = -1L;
        when(spaceshipRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> spaceshipService.getSpaceshipById(id));
        verify(spaceshipRepository, times(1)).findById(id);
    }
}
