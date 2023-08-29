package com.example.vacs.controllers;


import com.example.vacs.models.Auto;


import com.example.vacs.repositories.FirmRepository;
import com.example.vacs.repositories.ModelRepository;
import com.example.vacs.services.AutoService;

import com.example.vacs.services.FirmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Controller
@RequestMapping("/autos")
@RequiredArgsConstructor
public class AutoController {

    private final AutoService autoService;

    private final FirmService firmService;
//    private final ModelService modelService;


    @GetMapping()
    public String autos(Model model) {
        model.addAttribute("autos", autoService.findAll());
        return "autos";
    }

    @GetMapping("/{id}")
    public String autoInfo(@PathVariable("id") Long id, Model model) {
        Auto auto = autoService.getAutoById(id);

        model.addAttribute("auto", auto);
        model.addAttribute("images", auto.getImages());
        return "auto-info";
    }

    @GetMapping("/new")
    public String newAuto(Model model, @ModelAttribute("auto") Auto auto) {
        model.addAttribute("firms", firmService.getFirmsList());
//        model.addAttribute("models", modelService.getModelByFirmId());
        model.addAttribute("auto", new Auto());

        return "new";
    }


//

    @PostMapping()
    public String createAuto(Model model, @ModelAttribute("auto") @Valid Auto auto, BindingResult bindingResult,
                             @RequestParam("photo") MultipartFile[] files)

            throws IOException {
        if (bindingResult.hasErrors())
            return "new";
        model.addAttribute("auto", autoService.findAll());

        autoService.saveAuto(auto, files);
        return "redirect:/autos";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("auto", autoService.getAutoById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("auto") @Valid Auto auto,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "edit";

        autoService.updateAuto(id, auto);
        return "redirect:/autos";
    }

    @DeleteMapping("/{id}")
    public String deleteAuto(@PathVariable("id") Long id) {
        autoService.deleteAuto(id);
        return "redirect:/autos";
    }

    @GetMapping("/home")
    public String autos() {
        return "home";
    }
}