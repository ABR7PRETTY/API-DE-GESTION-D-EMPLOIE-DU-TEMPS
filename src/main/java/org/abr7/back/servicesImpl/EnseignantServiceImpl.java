package org.abr7.back.servicesImpl;

import org.abr7.back.dao.EnseignantDao;
import org.abr7.back.models.Enseignant;
import org.abr7.back.services.EnseignantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "enseignantService")
public class EnseignantServiceImpl implements EnseignantService {
    @Autowired
    private EnseignantDao enseignantDao;

    @Override
    public List<Enseignant> findAll() {
        return this.enseignantDao.findAll();
    }

    @Override
    public Enseignant save(Enseignant enseignant) {
        return this.enseignantDao.save(enseignant);
    }

    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.enseignantDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @Override
    public Enseignant update(Long id, Enseignant enseignant)  {
        Enseignant EnseignantExecting = this.enseignantDao.findById(id).orElseThrow(()->new RuntimeException("enseignant not found"));
        BeanUtils.copyProperties(enseignant, EnseignantExecting);
        return
                this.enseignantDao.save(EnseignantExecting);
    }

    public List<Enseignant> rechercher(String recherche) {
        return this.enseignantDao.findByCodeOrLibelle(recherche);
    }

}
