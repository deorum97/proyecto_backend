package com.example.jcoffe.service;

import com.example.jcoffe.model.Pedido;
import com.example.jcoffe.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jcoffe.DTO.PedidoDto;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido getPedidoById(Integer id){

        return pedidoRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Pedido no encontrado")
        );
    }

    public Pedido createPedido(Pedido ped){

        return pedidoRepository.save(ped);
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findLast(Integer usuarioId){
        return pedidoRepository.findByUsuarioIdAndEntregadoFalse(usuarioId);
    }


}
