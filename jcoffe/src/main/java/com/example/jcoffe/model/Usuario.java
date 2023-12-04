package com.example.jcoffe.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    @Column(length = 500)
    private String direccion;
    private String rol;

    public void update(Usuario user) {
        this.setNombre(user.getNombre());
        this.setEmail(user.getEmail());
        this.setContrasena(user.getContrasena());
        this.setTelefono(user.getTelefono());
        this.setDireccion(user.getDireccion());
        this.setRol(user.getRol());
    }
}
