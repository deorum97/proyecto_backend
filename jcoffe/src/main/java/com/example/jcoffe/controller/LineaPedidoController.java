package com.example.jcoffe.controller;

import com.example.jcoffe.model.LineaPedido;
import com.example.jcoffe.service.LineaPedidoService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.List;

@RestController
@RequestMapping("/api/lineasPedido")
public class LineaPedidoController {

    @Autowired
    private LineaPedidoService lineaPedidoService;

    @Autowired
    private Gson gson;

    @GetMapping("{id}")
    public ResponseEntity<?> getLineaPedidoById(@PathVariable Integer id){
        try{
            LineaPedido lineaPedido = lineaPedidoService.getLineaPedidoById(id);
            return ResponseEntity.ok().body(lineaPedido);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("create")
    public ResponseEntity<?> createLineaPedido(@RequestBody List<LineaPedido> lineas){
        try{
            List<LineaPedido> lineasPedido = lineaPedidoService.createLineaPedido(lineas);
            return ResponseEntity.ok().body(lineasPedido);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @GetMapping("pedido/{id}")
    public ResponseEntity<?> getAllLineaPedidoByPedido(@PathVariable Integer id){
        List<LineaPedido> listaPedido = lineaPedidoService.getAllLineasPedidoById(id);
        return ResponseEntity.ok().body(listaPedido);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllLineaPedido(){
        List<LineaPedido> listaPedido = lineaPedidoService.getAllLineasPedido();
        return ResponseEntity.ok().body(listaPedido);
    }
}
