package com.example.vacs.controllers;


import com.example.vacs.models.Auto;
import com.example.vacs.services.AutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/autos")
public class AutoController {

    private final AutoService autoService;


    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }


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
    public String newAuto(@ModelAttribute("auto") Auto auto) {
        return "new";
    }


//    @GetMapping("/")
//    public String searchAuto(Model model) {
//        model.addAttribute("brand", autoService.findAll());
//        return "autos";
//    }

    @PostMapping()
    public String createAuto(@ModelAttribute("auto") @Valid Auto auto, BindingResult bindingResult,
                             @RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3) throws IOException {
        if (bindingResult.hasErrors())
            return "new";

        autoService.saveAuto(auto, file1, file2, file3);
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
}
