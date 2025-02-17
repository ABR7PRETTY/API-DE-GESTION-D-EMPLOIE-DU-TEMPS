package org.abr7.back.dao;

import org.abr7.back.models.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatiereDao extends JpaRepository<Matiere, Long>{
    public List<Matiere> getByCode(String code);
    @Query("SELECT e FROM Matiere e WHERE e.code LIKE %:recherche% OR e.libelle LIKE %:recherche%")
    List<Matiere> findByCodeOrLibelle(@Param("recherche") String recherche);

}
