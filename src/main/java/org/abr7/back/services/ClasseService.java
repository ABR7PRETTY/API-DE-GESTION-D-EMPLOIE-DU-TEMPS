package org.abr7.back.services;

import org.abr7.back.models.Classe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "classeService")
public interface ClasseService {
    public List<Classe> findAll();

    public Classe save(Classe classe);

    public Boolean deleteById(Long id);

    public Classe update(Long id, Classe classe);

}
