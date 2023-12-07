package com.example.jcoffe.service;

import com.example.jcoffe.model.LineaPedido;
import com.example.jcoffe.repository.LineaPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaPedidoService {

    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;

    public LineaPedido getLineaPedidoById(Integer id){
        return lineaPedidoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Line de de pedido no encontrada")
        );
    }

    public List<LineaPedido> createLineaPedido(List<LineaPedido> lineas){
        return lineaPedidoRepository.saveAll(lineas);
    }

    public List<LineaPedido> getAllLineasPedidoById(Integer id){
        return lineaPedidoRepository.findByIdPedido(id);
    }

    public List<LineaPedido> getAllLineasPedido(){
        return lineaPedidoRepository.findAll();
    }



}
