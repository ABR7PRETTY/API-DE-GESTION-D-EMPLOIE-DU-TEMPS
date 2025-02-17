package org.abr7.back.controllers;


import org.abr7.back.models.Matiere;
import org.abr7.back.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/matiere")
public class MatiereController {

    @Autowired
    @Qualifier(value = "matiereService")
    private MatiereService matiereService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<Matiere> findAll() {
        List<Matiere> matieres = new ArrayList<>();
        try {
            matieres = this.matiereService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matieres;
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public Matiere save(@RequestBody Matiere matiere) {
        try {
            matiere = this.matiereService.save(matiere);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matiere;
    }


    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<Matiere> update(@PathVariable("id") Long id, @RequestBody Matiere matiere){
        return ResponseEntity.status(HttpStatus.OK).body(this.matiereService.update(id, matiere));
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.matiereService.deleteById(id);
            if(resultat){
                return  ResponseEntity.ok().body(" Le cours a été supprimé avec succès");
            }else{
                return ResponseEntity.badRequest().body("Echec de la suppression");
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Une erreur interne est survenue");
        }
    }

    @GetMapping(value = "/recherche/{recherche}", headers = "Accept=application/json")
    public ResponseEntity<List<Matiere>> allMatiere(@PathVariable String recherche){
        return ResponseEntity.status(HttpStatus.OK).body(this.matiereService.rechercher(recherche));
    }

}
