package com.example.jcoffe.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LineaPedido")
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numeroLinea;
    @ManyToOne
    @JoinColumn(name = "pedido_id", updatable = false, insertable = false)
    private Pedido pedido;
    @Column(name = "pedido_id")
    private Integer idPedido;
    @ManyToOne
    @JoinColumn(name = "cafe_id", updatable = false, insertable = false)
    private Cafe cafe;
    @Column(name= "cafe_id")
    private Integer idCafe;
    private Integer cantidad;
    private Double precioLinea;
}
