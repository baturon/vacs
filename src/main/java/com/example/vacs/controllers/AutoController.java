package com.example.vacs.controllers;


import com.example.vacs.models.Auto;
import com.example.vacs.services.AutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/autos")
public class AutoController {

    private final AutoService autoService;


    public AutoController( AutoService autoService) {
        this.autoService = autoService;
    }


    @GetMapping()
    public String autos(Model model) {
        model.addAttribute("autos", autoService.findAll());
        return "autos";
    }

    @GetMapping("/{id}")
    public String autoInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("auto", autoService.getAutoById(id));
        return "auto-info";
    }

    @GetMapping("/new")
    public String newAuto(@ModelAttribute("auto") Auto auto) {
        return "new";
    }


    @PostMapping()
    public String createAuto(@ModelAttribute("auto") @Valid Auto auto,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "new";

        autoService.saveAuto(auto);
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
        if(bindingResult.hasErrors())
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
