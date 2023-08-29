package com.example.vacs.services;

import com.example.vacs.models.Firm;
import com.example.vacs.repositories.FirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FirmService {
    private final FirmRepository firmRepository;

    public List<Firm> getFirmsList(){
        return firmRepository.findAll();
    }
    public Optional<Firm> getFirmById(Long id){
        return firmRepository.findById(id);
    }
}
