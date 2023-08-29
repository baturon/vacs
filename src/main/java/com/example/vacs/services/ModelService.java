package com.example.vacs.services;

import com.example.vacs.models.Model;
import com.example.vacs.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private ModelRepository modelRepository;

//    public List<Model> getModelByFirmId(Long firmId){
//        return modelRepository.findAllById(firmId);
//    }
}
