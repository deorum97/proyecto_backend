package com.example.jcoffe.service;

import com.example.jcoffe.model.Cafe;
import com.example.jcoffe.repository.CafeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafeService {

    @Autowired
    private CafeRepository cafeRepository;

    public Cafe getCafeById(Integer id){
        return cafeRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Cafe no encontrado")
        );
    }

    public Cafe createCafe(Cafe cafe){
        Optional<Cafe> cafeOpt = cafeRepository.findByNombre(cafe.getNombre());
        if(cafeOpt.isPresent()){
            throw new DataIntegrityViolationException("El cafe ya existe");
        }
        return cafeRepository.save(cafe);
    }

    public Cafe updateCafe(Cafe cafe, Integer id){
        Optional<Cafe> cafeOpt = cafeRepository.findById(id);
        if(cafeOpt.isPresent()){
            Cafe caf = cafeOpt.get();
            caf.update(cafe);
            return cafeRepository.save(caf);
        }
        throw  new EntityNotFoundException("Cafe no encontrado");
    }

    public void deleteCafe(Integer id){
        cafeRepository.deleteById(id);
    }

    public List<Cafe> getCafeByFamilia(Integer familiaId){
        return cafeRepository.findByFamiliaIdAndActivoIsTrue(familiaId);
    }

    public List<Cafe> findAll(){
        return cafeRepository.findAll();
    }

}
