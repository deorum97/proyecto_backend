package com.example.jcoffe.repository;

import com.example.jcoffe.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Integer> {
    Optional<Familia> findByNombre(String nombre);
}
