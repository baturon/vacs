package com.example.vacs.controllers;


import com.example.vacs.models.Auto;


import com.example.vacs.models.MaintenanceWork;
import com.example.vacs.models.NameWork;
import com.example.vacs.models.OtherWork;
import com.example.vacs.services.AutoService;

import com.example.vacs.services.FirmService;
import com.example.vacs.services.OtherWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/autos")
@RequiredArgsConstructor
public class AutoController {

    private final AutoService autoService;

    private final FirmService firmService;

    private final OtherWorkService otherWorkService;

    @GetMapping()
    public String autos(Model model) {
        model.addAttribute("autos", autoService.findAll());
        model.addAttribute("data", LocalDate.now());
        return "autos";
    }

    @GetMapping("/{id}")
    public String autoInfo(@PathVariable("id") Long id, Model model) {
        Auto auto = autoService.getAutoById(id);
//        System.out.println(autoService.getMaintenanceWorkLastChangeOil(auto));
        model.addAttribute("maintenanceWorkLastChangeOil", autoService.getMaintenanceWorkLastChangeOil(auto));
        model.addAttribute("maintenanceWorkLastChangeGRM", autoService.getMaintenanceWorkLastChangeGRM(auto));
        model.addAttribute("auto", auto);
        model.addAttribute("images", auto.getImages());
        model.addAttribute("data", LocalDate.now());

        return "auto-info";
    }


    @GetMapping("/new")
    public String newAuto(Model model) {


        model.addAttribute("firms", firmService.getFirmsList());
//        model.addAttribute("models", modelService.getModelByFirmId());
        model.addAttribute("auto", new Auto());
        List<Integer> listYears = new ArrayList<>();
        for (int i = LocalDateTime.now().getYear(); i >= 1960; i--) {
            listYears.add(i);
        }

        model.addAttribute("years", listYears);

        return "new";
    }


    @PostMapping("/create/auto")
    public String createAuto(Model model, @ModelAttribute("auto") @Valid Auto auto, BindingResult bindingResult,
                             @RequestParam("photo") MultipartFile[] files)
            throws IOException {
        if (bindingResult.hasErrors())
            return "/new";
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
    public String update(@ModelAttribute("auto") @Valid Auto auto, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) return "edit";

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


    @GetMapping("/photo/{id}")
    public String photoDamageAuto(@PathVariable("id") Long id, Model model) {
        Auto auto = autoService.getAutoById(id);

        model.addAttribute("auto", auto);
        model.addAttribute("images", auto.getImages());
        return "photo-damage";
    }

    @PatchMapping("/damagePhoto/add/{id}")
    public String addDamagePhotoAuto(@PathVariable("id") Long id,
                                     @RequestParam("photo") MultipartFile[] files) throws IOException {
        autoService.updatePhotoDamageAuto(id, files);
        return "redirect:/autos/photo/{id}";
    }

    @GetMapping("/maintenance-work/{id}")
    public String maintenanceWork(@PathVariable("id") Long id, Model model) {
        Auto auto = autoService.getAutoById(id);
        MaintenanceWork maintenanceWorkOil = new MaintenanceWork();
        maintenanceWorkOil.setNameWork(NameWork.CHANGE_OIL);
        MaintenanceWork maintenanceWorkGRM = new MaintenanceWork();
        maintenanceWorkGRM.setNameWork(NameWork.CHANGE_GRM);

        model.addAttribute("auto", auto);
//        model.addAttribute("maintenanceWorkList", auto.getMaintenanceWorkList());
        model.addAttribute("maintenanceWorkListOil", autoService.getMaintenanceWorkListOil(auto));
        model.addAttribute("maintenanceWorkListGRM", autoService.getMaintenanceWorkListGRM(auto));
        model.addAttribute("maintenanceWorkLastChangeOil", autoService.getMaintenanceWorkLastChangeOil(auto));
        model.addAttribute("maintenanceWorkLastChangeGRM", autoService.getMaintenanceWorkLastChangeGRM(auto));
        model.addAttribute("maintenanceWorkOil", maintenanceWorkOil);
        model.addAttribute("maintenanceWorkGRM", maintenanceWorkGRM);
        return "maintenance-work";
    }

    @PostMapping("/create/maintenance-work/{id}")
    public String addMaintenanceWorkToAuto(@PathVariable("id") Long id,
                                           @ModelAttribute("maintenanceWorkOil") MaintenanceWork maintenanceWorkOil,
                                           @ModelAttribute("maintenanceWorkGRM") MaintenanceWork maintenanceWorkGRM,
                                           @ModelAttribute("auto") Auto auto, Model model) {
        model.addAttribute("auto", autoService.getAutoById(id));

        autoService.saveMaintenanceWorkAuto(id, maintenanceWorkOil);
        return "redirect:/autos/maintenance-work/{id}";
    }

    @GetMapping("/other-work/{id}")
    public String otherWork(@PathVariable("id") Long id, Model model) {
        Auto auto = autoService.getAutoById(id);
        model.addAttribute("auto", auto);
        model.addAttribute("otherWorkList", auto.getOtherWorksList());
        model.addAttribute("otherWork", new OtherWork());
        model.addAttribute("startDate", new Date());
        model.addAttribute("endDate", new Date());

        return "other-work";
    }

    @GetMapping("/other-work-sorted/{id}")
    public String otherWorkSortedByDate(@RequestParam("startDate") String startDate,
                                                      @RequestParam("endDate") String endDate,
                                        @PathVariable("id") Long id, Model model) throws ParseException {
        Auto auto = autoService.getAutoById(id);
        model.addAttribute("auto", auto);
        model.addAttribute("otherWorkListSortedByDate",
                otherWorkService.getSortedListOtherWorkByDate(auto, startDate, endDate));

        model.addAttribute("startDate", LocalDate.parse(startDate));
        model.addAttribute("endDate", LocalDate.parse(endDate));
        return "other-work-sorted";
    }

    @PostMapping("/create/other-work/{id}")
    public String addOtherWorkToAuto(@PathVariable("id") Long id,
                                     @ModelAttribute("otherWork") OtherWork otherWork,
                                     @ModelAttribute("auto") Auto auto, Model model) {
        model.addAttribute("auto", autoService.getAutoById(id));

        autoService.saveOtherWorkAuto(id, otherWork);
        return "redirect:/autos/other-work/{id}";
    }

    @PatchMapping("/update/mileage/auto/{id}")
    public String UpdateMileageAuto(@ModelAttribute("auto") Auto auto,
                                    @PathVariable("id") Long id) {

        autoService.updateMileageAuto(id, auto);
        return "redirect:/autos/{id}";
    }

    @PostMapping("/updateDateEndInsurance/auto/{id}")
    public String updateDateEndInsurance(@ModelAttribute("auto") Auto auto,
                                         @PathVariable("id") Long id) {

        autoService.updateDateEndInsurance(id, auto);
        return "redirect:/autos/{id}";
    }

    @PostMapping("/updateDateCompletionTechnicalInspection/auto/{id}")
    public String updateDateCompletionTechnicalInspection(@ModelAttribute("auto") Auto auto,
                                                          @PathVariable("id") Long id) {

        autoService.updateDateCompletionTechnicalInspection(id, auto);
        return "redirect:/autos/{id}";
    }


}