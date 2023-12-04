package com.example.jcoffe.service;

import com.example.jcoffe.model.Familia;
import com.example.jcoffe.repository.FamiliaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliaService {

    @Autowired
    private FamiliaRepository familiaRepository;

    public Familia getFamiliaById(Integer id){
        return familiaRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Familia no encontrada")
        );
    }

    public Familia createFamilia(Familia fam){
        Optional<Familia> familiaOpt = familiaRepository.findByNombre(fam.getNombre());
        if(familiaOpt.isPresent()){
            throw new DataIntegrityViolationException("La familia ya existe");
        }
        return familiaRepository.save(fam);
    }

    public Familia updateFamilia(Familia fam, Integer id) {
        Optional<Familia> familiaOpt = familiaRepository.findById(id);
        if(familiaOpt.isPresent()){
            Familia familia = familiaOpt.get();
            familia.update(fam);
            return familiaRepository.save(familia);
        }
        throw new EntityNotFoundException("Familia no encontrada");
    }

    public void deleteFamiliaById(Integer id) {
        familiaRepository.deleteById(id);
    }

    public List<Familia> findAll(){
        return familiaRepository.findAll();
    }
}
