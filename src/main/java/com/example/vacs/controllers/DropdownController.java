package com.example.vacs.controllers;

import com.example.vacs.models.Firm;
import com.example.vacs.models.Model;
import com.example.vacs.services.FirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DropdownController {

    private final FirmService firmService;

    @GetMapping("/firms")
    public List<Firm> getListFirm() {
        return firmService.getFirmsList();
    }

    @GetMapping("/models/{id}")
    public List<Model> getModelsByFirmId(@PathVariable("id") Long id) {
        return firmService.getFirmById(id).get().getModels();
    }

}
