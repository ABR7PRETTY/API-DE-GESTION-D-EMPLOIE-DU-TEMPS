package org.abr7.back.controllers;

import org.abr7.back.models.Annee;
import org.abr7.back.services.AnneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/annee")
public class AnneeController {

    @Autowired
    @Qualifier(value = "anneeService")
    private AnneeService anneeService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<Annee> findAll() {
        List<Annee> annees = new ArrayList<>();
        try {
            annees = this.anneeService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<Annee> update(@PathVariable("id") Long id, @RequestBody Annee annee){
        return ResponseEntity.status(HttpStatus.OK).body(this.anneeService.update(id, annee));
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public Annee save(@RequestBody Annee annee) {
        try {
            annee = this.anneeService.save(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annee;
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.anneeService.deleteById(id);
            if(resultat){
                return  ResponseEntity.ok().body(" L'année a été supprimé avec succès");
            }else{
                return ResponseEntity.badRequest().body("Echec de la suppression");
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Une erreur interne est survenue");
        }
    }




}
