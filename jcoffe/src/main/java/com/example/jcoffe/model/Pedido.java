package com.example.jcoffe.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Date fechaPedido;
    @ManyToOne
    @JoinColumn(name = "usuario_id",updatable = false,insertable = false)
    private Usuario usuario;
    @Column(name="usuario_id")
    private Integer usuarioId;
    //private Double precio;
    //private String metodoPago;
    private Boolean entregado;

    public void update(Pedido ped) {
        this.setEntregado(ped.getEntregado());
    }
}
