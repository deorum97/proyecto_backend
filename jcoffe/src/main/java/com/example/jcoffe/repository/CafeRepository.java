package com.example.jcoffe.repository;

import com.example.jcoffe.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    List<Cafe> findByFamiliaIdAndActivoIsTrue(Integer familiaId);
    Optional<Cafe> findByNombre(String nombre);

}
