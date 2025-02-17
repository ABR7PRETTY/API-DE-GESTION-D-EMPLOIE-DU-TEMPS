package org.abr7.back.dao;

import org.abr7.back.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnseignantDao extends JpaRepository<Enseignant, Long>{
    public List<Enseignant> getByNom(String code);

    @Query("SELECT e FROM Enseignant e WHERE e.nom LIKE %:recherche% OR e.prenom LIKE %:recherche%")
    List<Enseignant> findByCodeOrLibelle(@Param("recherche") String recherche);
}
