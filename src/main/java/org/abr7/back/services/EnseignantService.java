package org.abr7.back.services;

import org.abr7.back.models.Enseignant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "enseignantService")
public interface EnseignantService {
    public List<Enseignant> findAll();

    public Enseignant save(Enseignant enseignant);

    public Boolean deleteById(Long id);

    public Enseignant update(Long id, Enseignant enseignant);

    public List<Enseignant> rechercher(String recherche);
}
