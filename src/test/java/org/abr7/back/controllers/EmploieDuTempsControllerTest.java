package org.abr7.back.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.abr7.back.dao.EmploieDuTempsDao;
import org.abr7.back.models.EmploieDuTemps;
import org.abr7.back.services.EmploieDuTempsService;
import org.abr7.back.servicesImpl.EmploieDuTempsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.web.client.ExpectedCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class EmploieDuTempsControllerTest {

    @Mock
    private EmploieDuTempsDao emploieDuTempsDao;

    @InjectMocks
    private EmploieDuTempsServiceImpl emploieDuTempsService;

    @Test
    void CREATE() {
        // Création d'une liste d'emplois fictive
        List<EmploieDuTemps> emploieDuTempsList = new ArrayList<>();
        emploieDuTempsList.add(new EmploieDuTemps());
        emploieDuTempsList.add(new EmploieDuTemps());

        // Définition du comportement attendu lors de l'appel à findAll() du repository
        when(emploieDuTempsDao.findAll()).thenReturn(emploieDuTempsList);

        // Appel de la méthode findAll() du service
        List<EmploieDuTemps> result = emploieDuTempsService.findAll();

        // Vérification que la liste retournée par le service est la même que celle attendue
        assertEquals(emploieDuTempsList.size(), result.size());
    }

    @Test
    void READ() {
        // Création d'un emploi fictif
        EmploieDuTemps emploieDuTemps = new EmploieDuTemps();

        // Définition du comportement attendu lors de l'appel à lastItem() du repository
        when(emploieDuTempsDao.save(emploieDuTemps)).thenReturn(emploieDuTemps);

        // Définition du comportement attendu lors de l'appel à save() du repository
        when(emploieDuTempsDao.save(emploieDuTemps)).thenReturn(emploieDuTemps);

        // Appel de la méthode save() du service
        EmploieDuTemps result = emploieDuTempsService.save(emploieDuTemps);

        // Vérification que l'emploi retourné par le service est le même que celui attendu
        assertEquals(emploieDuTemps, result);
    }

    @Test
    void UPDATE() {
        Long id = 1L;
        EmploieDuTemps existingEmploieDuTemps = new EmploieDuTemps(); // Assume it has some initial state
        EmploieDuTemps updatedEmploieDuTemps = new EmploieDuTemps(); // Assume it reflects the desired state after update

        when(emploieDuTempsDao.findById(id)).thenReturn(Optional.of(existingEmploieDuTemps));
        when(emploieDuTempsDao.save(existingEmploieDuTemps)).thenReturn(updatedEmploieDuTemps);

        EmploieDuTemps result = emploieDuTempsService.update(id, updatedEmploieDuTemps); // Assuming this is how your service's update method is designed

        assertEquals(updatedEmploieDuTemps, result);
    }

    @Test
    void Delete() {

    }
}