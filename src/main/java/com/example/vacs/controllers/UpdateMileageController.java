package com.example.vacs.controllers;

import com.example.vacs.models.Auto;
import com.example.vacs.services.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UpdateMileageController {

    private final AutoService autoService;
    @PatchMapping("/update/mileage/auto/{id}")
    public String UpdateMileageAuto(@ModelAttribute("auto")  Auto auto,
                                    @PathVariable("id") Long id) {
//        if (bindingResult.hasErrors())
//            return "redirect:/autos";

        autoService.updateMileageAuto(id,auto);


        return "redirect:/autos/{id}";
    }

}
