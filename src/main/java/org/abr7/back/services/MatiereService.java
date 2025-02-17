package org.abr7.back.services;

import org.abr7.back.models.Matiere;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "matiereService")
public interface MatiereService {
    public List<Matiere> findAll();

    public Matiere save(Matiere matiere);


    public Boolean deleteById(Long id);

    public  Matiere update(Long id, Matiere matiere);

    public List<Matiere> rechercher(String recherche);

}
