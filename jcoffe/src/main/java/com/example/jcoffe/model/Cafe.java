package com.example.jcoffe.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Cafe")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    private Double precio;
    @Column(length = 500)
    private String URLImagen;
    private Boolean activo;
    @ManyToOne
    @JoinColumn(name = "familia_id", updatable = false, insertable = false)
    private Familia familia;
    @Column(name= "familia_id")
    private Integer familiaId;

    public void update(Cafe cafe) {
        this.setNombre(cafe.getNombre());
        this.setDescripcion(cafe.getDescripcion());
        this.setPrecio(cafe.getPrecio());
        this.setURLImagen(cafe.getURLImagen());
        this.setActivo(cafe.getActivo());
        this.setFamiliaId(cafe.getFamiliaId());
    }
}
