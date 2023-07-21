package com.example.vacs.services;

import com.example.vacs.models.Auto;
import com.example.vacs.repositories.AutoRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class AutoService {

    private final AutoRepository autoRepository;


    public AutoService( AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }


    public List<Auto> findAll() {
                return autoRepository.findAll();
    }

    @Transactional
    public void saveAuto(Auto auto) {
        autoRepository.save(auto);
    }
   @Transactional
    public void updateAuto(Long id, Auto updatedAuto) {
        updatedAuto.setId(id);
        autoRepository.save(updatedAuto);
    }
   @Transactional
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }

    public Auto getAutoById(Long id) {

        return autoRepository.findById(id).orElse(null);
    }
}
