package org.abr7.back.controllers;

import org.abr7.back.models.EmploieDuTemps;
import org.abr7.back.services.EmploieDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/emploieDuTemps")
public class EmploieDuTempsController {

    @Autowired
    @Qualifier(value = "emploieDuTempsService")
    private EmploieDuTempsService emploieDuTempsService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<EmploieDuTemps> findAll() {
        List<EmploieDuTemps> emploies = new ArrayList<>();
        try {
            emploies = this.emploieDuTempsService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emploies;
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public EmploieDuTemps save(@RequestBody EmploieDuTemps emploieDuTemps) {
        try {
            emploieDuTemps = this.emploieDuTempsService.save(emploieDuTemps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emploieDuTemps;
    }


    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<EmploieDuTemps> update(@PathVariable("id") Long id, @RequestBody EmploieDuTemps cours){
        return ResponseEntity.status(HttpStatus.OK).body(this.emploieDuTempsService.update(id, cours));
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.emploieDuTempsService.deleteById(id);
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
