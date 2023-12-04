package com.example.jcoffe.repository;

import com.example.jcoffe.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    Pedido findByUsuarioIdAndEntregadoFalse(Integer usuarioId);
    Pedido findByEntregado(Boolean entregado);
}
