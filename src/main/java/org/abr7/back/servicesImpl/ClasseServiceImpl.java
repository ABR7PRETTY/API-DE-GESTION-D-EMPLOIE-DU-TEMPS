package org.abr7.back.servicesImpl;

import org.abr7.back.dao.ClasseDao;
import org.abr7.back.models.Classe;
import org.abr7.back.services.ClasseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "classeService")
public class ClasseServiceImpl implements ClasseService {
    @Autowired
    private ClasseDao classeDao;

    @Override
    public List<Classe> findAll() {
        return this.classeDao.findAll();
    }

    @Override
    public Classe save(Classe classe) {
        return this.classeDao.save(classe);
    }

    @Override
    public Classe update(Long id, Classe classe)  {
        Classe ClasseExecting = this.classeDao.findById(id).orElseThrow(()->new RuntimeException("Classe not found"));
        BeanUtils.copyProperties(classe, ClasseExecting);
        return
                this.classeDao.save(ClasseExecting);
    }


    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.classeDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

}
