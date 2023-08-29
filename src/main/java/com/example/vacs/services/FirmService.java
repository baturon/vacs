package com.example.vacs.services;

import com.example.vacs.models.Firm;
import com.example.vacs.models.Model;
import com.example.vacs.repositories.FirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmService {
    private final FirmRepository firmRepository;

    public List<Firm> getFirmsList(){
        return firmRepository.findAll();
    }
}
