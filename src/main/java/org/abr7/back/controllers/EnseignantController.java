package org.abr7.back.controllers;

import org.abr7.back.models.Enseignant;
import org.abr7.back.services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/enseignant")
public class EnseignantController {

    @Autowired
    @Qualifier(value = "enseignantService")
    private EnseignantService enseignantService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<Enseignant> findAll() {
        List<Enseignant> enseignants = new ArrayList<>();
        try {
            enseignants = this.enseignantService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enseignants;
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public Enseignant save(@RequestBody Enseignant enseignant) {
        try {
            enseignant = this.enseignantService.save(enseignant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enseignant;
    }


    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<Enseignant> update(@PathVariable("id") Long id, @RequestBody Enseignant enseignant){
        return ResponseEntity.status(HttpStatus.OK).body(this.enseignantService.update(id, enseignant));
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.enseignantService.deleteById(id);
            if(resultat){
                return  ResponseEntity.ok().body(" L'enseignant a été supprimé avec succès");
            }else{
                return ResponseEntity.badRequest().body("Echec de la suppression");
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Une erreur interne est survenue");
        }
    }

    @GetMapping(value = "/recherche/{recherche}", headers = "Accept=application/json")
    public ResponseEntity<List<Enseignant>> allEnseignant(@PathVariable String recherche){
        return ResponseEntity.status(HttpStatus.OK).body(this.enseignantService.rechercher(recherche));
    }

}
