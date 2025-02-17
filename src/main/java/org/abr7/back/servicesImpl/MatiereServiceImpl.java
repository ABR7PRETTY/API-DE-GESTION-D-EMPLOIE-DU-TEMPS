package org.abr7.back.servicesImpl;

import org.abr7.back.dao.MatiereDao;
import org.abr7.back.models.Matiere;
import org.abr7.back.services.MatiereService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "matiereService")
public class MatiereServiceImpl implements MatiereService {
    @Autowired
    private MatiereDao matiereDao;

    @Override
    public List<Matiere> findAll() {
        return this.matiereDao.findAll();
    }

    @Override
    public Matiere save(Matiere matiere) {
        return this.matiereDao.save(matiere);
    }

    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.matiereDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @Override
    public Matiere update(Long id, Matiere matiere)  {
        Matiere MatiereExecting = this.matiereDao.findById(id).orElseThrow(()->new RuntimeException("matiere not found"));
        BeanUtils.copyProperties(matiere, MatiereExecting);
        return
                this.matiereDao.save(MatiereExecting);
    }

    public List<Matiere> rechercher(String recherche) {
        return this.matiereDao.findByCodeOrLibelle(recherche);
    }

}