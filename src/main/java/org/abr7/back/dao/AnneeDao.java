package org.abr7.back.dao;

import org.abr7.back.models.Annee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnneeDao extends JpaRepository<Annee, Long>{
    public List<Annee> getByCode(String code);
}
