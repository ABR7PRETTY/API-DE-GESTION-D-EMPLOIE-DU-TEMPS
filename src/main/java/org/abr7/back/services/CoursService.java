package org.abr7.back.services;

import org.abr7.back.models.Cours;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "coursService")
public interface CoursService {
    public List<Cours> findAll();

    public Cours save(Cours cours);

    public Cours update(Long id, Cours cours);

    public Boolean deleteById(Long id);

}
