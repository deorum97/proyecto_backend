package com.example.jcoffe.controller;

import com.example.jcoffe.model.Usuario;
import com.example.jcoffe.service.UsuarioService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Gson gson;

    @GetMapping("{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id){
        try{
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok().body(usuario);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @GetMapping("byEmail/{email}")
    public ResponseEntity<?> getUsuarioByEmail(@PathVariable String email){
        try{
            Usuario usuario = usuarioService.getUsuarioByEmail(email);
            return ResponseEntity.ok().body(usuario);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("create")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario user){
        try{
            Usuario usuario = usuarioService.createUsuario(user);
            return  ResponseEntity.ok().body(usuario);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    gson.toJson("No se puede crear el usuario.")
            );
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario user,@PathVariable Integer id){
        try{
            Usuario usuario = usuarioService.updateUsuario(user, id);
            return ResponseEntity.ok().body(usuario);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        List<Usuario> listaUsuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(listaUsuarios);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    public ResponseEntity<?> getUsuarioByEmailAndPassword(@RequestParam String email, @RequestParam String pass){
        try{
            Usuario usuario = usuarioService.getUsuarioByEmailAndPassword(email,pass);
            return ResponseEntity.ok().body(usuario);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
