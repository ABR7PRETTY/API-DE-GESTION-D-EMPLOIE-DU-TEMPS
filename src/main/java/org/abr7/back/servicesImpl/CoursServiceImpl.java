package org.abr7.back.servicesImpl;

import jakarta.persistence.EntityNotFoundException;
import org.abr7.back.dao.*;
import org.abr7.back.models.*;
import org.abr7.back.services.CoursService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "coursService")
public class CoursServiceImpl implements CoursService {


    private final MatiereDao matiereDAO;
    private final EnseignantDao enseignantDAO;
    private final ClasseDao classeDAO;
    private final AnneeDao anneeDAO;
    private final CoursDao coursDAO;

    public CoursServiceImpl(MatiereDao matiereDAO, EnseignantDao enseignantDAO,
                            ClasseDao classeDAO, AnneeDao anneeDAO,
                            CoursDao coursDAO) {
        this.matiereDAO = matiereDAO;
        this.enseignantDAO = enseignantDAO;
        this.classeDAO = classeDAO;
        this.anneeDAO = anneeDAO;
        this.coursDAO = coursDAO;
    }

    @Override
    public Cours save(Cours cours) {
        Optional<Matiere> optionalMatiere = matiereDAO.findById(cours.getMatiere().getId());
        Optional<Enseignant> optionalEnseignant = enseignantDAO.findById(cours.getEnseignant().getId());
        Optional<Classe> optionalClasse = classeDAO.findById(cours.getClasse().getId());
        Optional<Annee> optionalAnnee = anneeDAO.findById(cours.getAnnee().getId());

        // Vérifiez si les objets Matiere, Enseignant, Classe et Annee existent dans la base de données
        if (optionalMatiere.isPresent() && optionalEnseignant.isPresent()
                && optionalClasse.isPresent() && optionalAnnee.isPresent()) {
            Matiere matiere = optionalMatiere.get();
            Enseignant enseignant = optionalEnseignant.get();
            Classe classe = optionalClasse.get();
            Annee annee = optionalAnnee.get();

            cours.setMatiere(matiere);
            cours.setEnseignant(enseignant);
            cours.setClasse(classe);
            cours.setAnnee(annee);

            return coursDAO.save(cours);
        } else {
            // Gérer le cas où un objet n'est pas trouvé dans la base de données
            throw new EntityNotFoundException("One of the associated entities not found");
        }
    }

    @Autowired
    private CoursDao coursDao;

    @Override
    public List<Cours> findAll() {
        return this.coursDao.findAll();
    }



    @Override
    public Boolean deleteById(Long id)  {
        Boolean resultat = false;
        try {
            this.coursDao.deleteById(id);
            resultat = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }


    @Override
    public Cours  update(Long id,Cours cours)  {
        Cours CoursExecting = this.coursDao.findById(id).orElseThrow(()->new RuntimeException("Cours not found"));
        BeanUtils.copyProperties(cours, CoursExecting);
        return
                this.coursDao.save(CoursExecting);
    }

}
