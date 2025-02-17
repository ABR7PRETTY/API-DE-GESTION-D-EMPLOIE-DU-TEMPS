package org.abr7.back.servicesImpl;

import org.abr7.back.dao.AnneeDao;
import org.abr7.back.models.Annee;
import org.abr7.back.services.AnneeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "anneeService")
public class AnneeServiceImpl implements AnneeService {
    @Autowired
    private AnneeDao anneeDao;

    @Override
    public List<Annee> findAll() {
        return this.anneeDao.findAll();
    }

    @Override
    public Annee save(Annee annee) {
        return this.anneeDao.save(annee);
    }


    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.anneeDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    @Override
    public Annee update(Long id, Annee annee)  {
        Annee AnneeExecting = this.anneeDao.findById(id).orElseThrow(()->new RuntimeException("Classe not found"));
        BeanUtils.copyProperties(annee, AnneeExecting);
        return
                this.anneeDao.save(AnneeExecting);
    }


}