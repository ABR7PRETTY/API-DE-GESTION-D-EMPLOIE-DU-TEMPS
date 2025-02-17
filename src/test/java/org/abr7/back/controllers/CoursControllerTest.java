package org.abr7.back.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.abr7.back.dao.CoursDao;
import org.abr7.back.models.Cours;
import org.abr7.back.services.CoursService;
import org.abr7.back.servicesImpl.CoursServiceImpl;
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
class CoursControllerTest {

    @Mock
    private CoursDao coursDao;

    @InjectMocks
    private CoursServiceImpl coursService;

    @Test
    void CREATE() {
        // Création d'une liste d'emplois fictive
        List<Cours> coursList = new ArrayList<>();
        coursList.add(new Cours());
        coursList.add(new Cours());

        // Définition du comportement attendu lors de l'appel à findAll() du repository
        when(coursDao.findAll()).thenReturn(coursList);

        // Appel de la méthode findAll() du service
        List<Cours> result = coursService.findAll();

        // Vérification que la liste retournée par le service est la même que celle attendue
        assertEquals(coursList.size(), result.size());
    }

    @Test
    void READ() {
        // Création d'un emploi fictif
        Cours cours = new Cours();

        // Définition du comportement attendu lors de l'appel à lastItem() du repository
        when(coursDao.save(cours)).thenReturn(cours);

        // Définition du comportement attendu lors de l'appel à save() du repository
        when(coursDao.save(cours)).thenReturn(cours);

        // Appel de la méthode save() du service
        Cours result = coursService.save(cours);

        // Vérification que l'emploi retourné par le service est le même que celui attendu
        assertEquals(cours, result);
    }

    @Test
    void UPDATE (){
        Long id = 1L;
        Cours existingCours = new Cours(); // Assume it has some initial state
        Cours updatedCours = new Cours(); // Assume it reflects the desired state after update

        when(coursDao.findById(id)).thenReturn(Optional.of(existingCours));
        when(coursDao.save(existingCours)).thenReturn(updatedCours);

        Cours result = coursService.update(id, updatedCours); // Assuming this is how your service's update method is designed

        assertEquals(updatedCours, result);
    }

    @Test
    void DELETE() {

    }
}