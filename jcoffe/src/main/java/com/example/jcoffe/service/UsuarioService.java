package com.example.jcoffe.service;

import com.example.jcoffe.model.Usuario;
import com.example.jcoffe.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("No se encuentra el usuario")
        );
    }

    public Usuario getUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new EntityNotFoundException("No se encuentra el email {email}")
        );
    }

    public Usuario createUsuario(Usuario user){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(user.getEmail());
        if(usuarioOpt.isPresent()){
            throw new DataIntegrityViolationException("Usuario ya existente");
        }
        return usuarioRepository.save(user);
    }

    public Usuario updateUsuario(Usuario user, Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            usuario.update(user);
            return usuarioRepository.save(usuario);
        }
        throw new EntityNotFoundException("Usuario no encontrado");
    }

    public void deleteUsuarioById(Integer id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioByEmailAndPassword(String email, String pass){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if(usuarioOpt.isPresent()){
            String passw = usuarioOpt.get().getContrasena();
            if(passw.equals(pass)){
                Usuario usuario = usuarioOpt.get();
                return usuario;
            }
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        throw new EntityNotFoundException("Usuario no encontrado");
    }
}
