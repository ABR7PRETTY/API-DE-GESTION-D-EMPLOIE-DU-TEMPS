package org.abr7.back.dao;

import org.abr7.back.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClasseDao extends JpaRepository<Classe, Long>{
    public List<Classe> getByCode(String code);
}
