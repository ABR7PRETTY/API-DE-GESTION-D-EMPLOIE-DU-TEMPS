package org.abr7.back.services;

import org.abr7.back.models.Annee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "anneeService")
public interface AnneeService {
    public List<Annee> findAll();

    public Annee save(Annee annee);

    public Boolean deleteById(Long id);

    public Annee update(Long id,Annee annee);


}
