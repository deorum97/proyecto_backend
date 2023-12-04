package com.example.jcoffe.DTO;

import java.io.Serializable;
import java.util.Date;

public class PedidoDto implements Serializable {
    private Date fechaPedido;
    private Integer usuarioId;
    private String metodoPago;
    private Boolean entregado;
}
