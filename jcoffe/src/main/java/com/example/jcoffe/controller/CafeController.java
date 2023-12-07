package com.example.jcoffe.controller;

import com.example.jcoffe.model.Cafe;
import com.example.jcoffe.service.CafeService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cafes")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private Gson gson;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("{id}")
    public ResponseEntity<?> getCafeById(@PathVariable Integer id){
        try {
            Cafe cafe = cafeService.getCafeById(id);
            return ResponseEntity.ok().body(cafe);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }

    }

    @PostMapping("create")
    public ResponseEntity<?> createCafe(@RequestBody Cafe caf){
        try{
            Cafe cafe = cafeService.createCafe(caf);
            return ResponseEntity.ok().body(cafe);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCafe(@RequestBody Cafe caf, @PathVariable Integer id){
        try{
            Cafe cafe = cafeService.updateCafe(caf, id);
            return ResponseEntity.ok().body(cafe);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable Integer id){
        cafeService.deleteCafe(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("familia/{familiaId}")
    public ResponseEntity<?> getCafeByFamilia(@PathVariable Integer familiaId){
        List<Cafe> listaCafes = cafeService.getCafeByFamilia(familiaId);
        return ResponseEntity.ok().body(listaCafes);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        List<Cafe> listaCafes = cafeService.findAll();
        return ResponseEntity.ok().body(listaCafes);
    }
}
