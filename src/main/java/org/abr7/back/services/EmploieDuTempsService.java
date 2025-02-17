package org.abr7.back.services;

import org.abr7.back.models.EmploieDuTemps;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "emploieDuTempsService")
public interface EmploieDuTempsService {
    public List<EmploieDuTemps> findAll();

    public EmploieDuTemps save(EmploieDuTemps emploieDuTemps);

    public EmploieDuTemps update(Long id, EmploieDuTemps emploieDuTemps);

    public Boolean deleteById(Long id);

}
