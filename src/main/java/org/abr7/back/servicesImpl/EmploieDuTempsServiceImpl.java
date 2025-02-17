package org.abr7.back.servicesImpl;

import org.abr7.back.dao.EmploieDuTempsDao;
import org.abr7.back.models.EmploieDuTemps;
import org.abr7.back.services.EmploieDuTempsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "emploieDuTempsService")
public class EmploieDuTempsServiceImpl implements EmploieDuTempsService {
    @Autowired
    private EmploieDuTempsDao emploieDuTempsDao;

    @Override
    public List<EmploieDuTemps> findAll() {
        return this.emploieDuTempsDao.findAll();
    }

    @Override
    public EmploieDuTemps save(EmploieDuTemps emploieDuTemps) {
        return this.emploieDuTempsDao.save(emploieDuTemps);
    }

    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.emploieDuTempsDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }


    @Override
    public EmploieDuTemps  update(Long id,EmploieDuTemps emploieDuTemps)  {
        EmploieDuTemps emploieDuTempsExecting = this.emploieDuTempsDao.findById(id).orElseThrow(()->new RuntimeException("Cours not found"));
        BeanUtils.copyProperties(emploieDuTemps, emploieDuTempsExecting);
        return
                this.emploieDuTempsDao.save(emploieDuTempsExecting);
    }

}
