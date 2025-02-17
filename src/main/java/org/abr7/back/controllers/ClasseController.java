package org.abr7.back.controllers;


import org.abr7.back.models.Classe;
import org.abr7.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "service/classe")
public class ClasseController {

    @Autowired
    @Qualifier(value = "classeService")
    private ClasseService classeService;

    @GetMapping(value = "/findAll", headers = "Accept=application/json")
    public List<Classe> findAll() {
        List<Classe> classes = new ArrayList<>();
        try {
            classes = this.classeService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public Classe save(@RequestBody Classe classe) {
        try {
            classe = this.classeService.save(classe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classe;
    }


    @PutMapping(value = "/update/{id}", headers = "Accept=application/json")
    public ResponseEntity<Classe> update(@PathVariable("id") Long id, @RequestBody Classe classe){
        return ResponseEntity.status(HttpStatus.OK).body(this.classeService.update(id, classe));
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<?>delete(@PathVariable Long id){
        try{
            Boolean resultat = this.classeService.deleteById(id);
            if(resultat){
                return  ResponseEntity.ok().body(" La classe a été supprimée avec succès");
            }else{
                return ResponseEntity.badRequest().body("Echec de la suppression");
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Une erreur interne est survenue");
        }
    }

}
