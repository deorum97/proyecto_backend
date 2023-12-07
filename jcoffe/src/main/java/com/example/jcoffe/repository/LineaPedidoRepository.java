package com.example.jcoffe.repository;

import com.example.jcoffe.model.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Integer> {
    List<LineaPedido> findByIdPedido(Integer idPedido);
}
