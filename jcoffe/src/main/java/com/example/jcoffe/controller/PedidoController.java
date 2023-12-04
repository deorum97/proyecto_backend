package com.example.jcoffe.controller;

import com.example.jcoffe.DTO.PedidoDto;
import com.example.jcoffe.model.Pedido;
import com.example.jcoffe.service.PedidoService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private Gson gson;

    @GetMapping("{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Integer id){
        try{
            Pedido pedido = pedidoService.getPedidoById(id);
            return ResponseEntity.ok().body(pedido);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    gson.toJson(e.getMessage())
            );
        }
    }

    @PostMapping("{create}")
    public ResponseEntity<?> createPedido(@RequestBody Pedido ped){
        try{
            Pedido pedido = pedidoService.createPedido(ped);
            return ResponseEntity.ok().body(ped);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "No se ha podido crear el pedido"
            );
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        List<Pedido> listaPedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(listaPedidos);
    }

    @GetMapping("entregado/{id}")
    public ResponseEntity<?> findByEntregado(@PathVariable Integer id){
        Pedido pedido = pedidoService.findLast(id);
        return ResponseEntity.ok().body(pedido);
    }
}
