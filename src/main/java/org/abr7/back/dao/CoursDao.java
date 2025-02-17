package org.abr7.back.dao;

import org.abr7.back.models.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CoursDao extends JpaRepository<Cours, Long>{
    public List<Cours> getByCode(String code);
}
