package org.abr7.back.controllers;

import org.abr7.back.models.Cours;
import org.abr7.back.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/cours")
public class CoursController {

    @Autowired
    @Qualifier(value = "coursService")
    private CoursService coursService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<Cours> findAll() {
        List<Cours> courss = new ArrayList<>();
        try {
            courss = this.coursService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courss;
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public Cours save(@RequestBody Cours cours) {
        try {
            cours = this.coursService.save(cours);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cours;
    }


    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<Cours> update(@PathVariable("id") Long id, @RequestBody Cours cours){
        return ResponseEntity.status(HttpStatus.OK).body(this.coursService.update(id, cours));
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.coursService.deleteById(id);
            if(resultat){
                return  ResponseEntity.ok().body(" Le cours a été supprimé avec succès");
            }else{
                return ResponseEntity.badRequest().body("Echec de la suppression");
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Une erreur interne est survenue");
        }
    }

}
