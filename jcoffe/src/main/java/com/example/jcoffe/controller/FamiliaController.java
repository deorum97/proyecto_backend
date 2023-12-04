package com.example.jcoffe.controller;

import com.example.jcoffe.model.Familia;
import com.example.jcoffe.service.FamiliaService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/familias")
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private Gson gson;

    @GetMapping("{id}")
    public ResponseEntity<?> getFamiliaById(@PathVariable Integer id){
        try {
            Familia familia = familiaService.getFamiliaById(id);
            return ResponseEntity.ok().body(familia);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createFamilia(@RequestBody Familia fam){
        try{
            Familia familia = familiaService.createFamilia(fam);
            return ResponseEntity.ok().body(familia);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    gson.toJson("No se ha podido crear la familia.")
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFamilia(@RequestBody Familia fam, @PathVariable Integer id){
        try{
            Familia familia = familiaService.updateFamilia(fam, id);
            return ResponseEntity.ok().body(familia);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFamilia(@PathVariable Integer id){
        familiaService.deleteFamiliaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        List<Familia> listaFamilia = familiaService.findAll();
        return ResponseEntity.ok().body(listaFamilia);
    }
}
