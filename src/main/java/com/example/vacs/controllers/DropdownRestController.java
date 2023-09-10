package com.example.vacs.controllers;

import com.example.vacs.models.Firm;
import com.example.vacs.models.Model;
import com.example.vacs.services.FirmService;
import lombok.RequiredArgsConstructor;

import com.example.vacs.repositories.FirmRepository;
import com.example.vacs.repositories.ModelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DropdownRestController {

    private final FirmService firmService;

    @GetMapping("/firms")
    public List<Firm> getListFirm() {
        return firmService.getFirmsList();
    }

    @GetMapping("/models/{id}")
    public List<Model> getModelsByFirmId(@PathVariable("id") Long id) {
        return firmService.getFirmById(id).get().getModels();
    }

    private final FirmRepository firmRepository;
    private final ModelRepository modelRepository;



    @GetMapping("/firm")
    public List<Firm> getAllFirms() {
        return firmRepository.findAll();
    }

//    @GetMapping("/model/{firmId}")
//    public List<Model> getModelsByFirm(@PathVariable("firmId") Long firmId) {
//        return modelRepository.findAllByFirm_Id(firmId);
//    }
}
