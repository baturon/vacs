package com.example.vacs.services;

import com.example.vacs.models.*;
import com.example.vacs.repositories.AutoRepository;


import com.example.vacs.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AutoService {

    private final AutoRepository autoRepository;


    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }


    public List<Auto> findAll() {
        return autoRepository.findAll();
    }



    @Transactional
    public void saveAuto(Auto auto, MultipartFile file) throws IOException {
        List<Image> images = new ArrayList<>();

        if (file.getSize() != 0) {
            Image image = imagesToEntity(file);
            images.add(image);
            images.get(0).setPreviewImage(true);
            auto.addImageToAuto(image);
        }
        auto.setDateChangeMileage(LocalDate.now());
        autoRepository.save(auto);

    }


    private Image imagesToEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setDateOfDownload(LocalDate.now());
        return image;
    }


    @Transactional
    public void updateAuto(Long id, Auto updatedAuto) {
        updatedAuto.setId(id);
        autoRepository.save(updatedAuto);
    }

    @Transactional
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }

    public Auto getAutoById(Long id) {

        return autoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateMileageAuto(Long id, Auto updatedAuto) {
        Auto auto;

        auto = autoRepository.findById(id).get();
        auto.setDateChangeMileage(LocalDate.now());
        auto.setMileage(updatedAuto.getMileage());
        auto.setDaysReminderChangeMileage(updatedAuto.getDaysReminderChangeMileage());
//        auto.setId(id);

        autoRepository.save(auto);
    }

    @Transactional
    public void updatePhotoDamageAuto(Long id, MultipartFile[] files) throws IOException {
        Auto auto = autoRepository.findById(id).get();
        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                auto.setId(id);
//                auto.getImages().add(imagesToEntity(file));
                auto.addImageToAuto(imagesToEntity(file));
            }
        }
        autoRepository.save(auto);

    }

    @Transactional
    public void saveMaintenanceWorkAuto(Long id, MaintenanceWork maintenanceWork) {
        Auto auto = autoRepository.findById(id).get();

        //maintenanceWork.setNameWork(NameWork.CHANGE_OIL);

        maintenanceWork.setMileageNextChange(maintenanceWork.getNextReplacementIn() + maintenanceWork.getMileageChange());
        List<MaintenanceWork> maintenanceWorkList = auto.getMaintenanceWorkList();
        maintenanceWorkList.add(maintenanceWork);
        auto.setId(id);
        auto.addMaintenanceWorkToAuto(maintenanceWork);

        autoRepository.save(auto);

    }

    @Transactional
    public List<MaintenanceWork> getMaintenanceWorkListOil(Auto auto) {
        List<MaintenanceWork> maintenanceWorkListOil = new ArrayList<>();
        for (MaintenanceWork maintenanceWork : auto.getMaintenanceWorkList()) {
            if (maintenanceWork.getNameWork() == NameWork.CHANGE_OIL) {
                maintenanceWorkListOil.add(maintenanceWork);
            }
        }
        Collections.sort(maintenanceWorkListOil, Comparator.comparing(MaintenanceWork::getDateChange));
        return maintenanceWorkListOil;
    }


    @Transactional
    public MaintenanceWork getMaintenanceWorkLastChangeOil(Auto auto) {
        MaintenanceWork maintenanceWorkLastChangeOil;
        if (getMaintenanceWorkListOil(auto).isEmpty()) {
            maintenanceWorkLastChangeOil = null;
        } else {
            maintenanceWorkLastChangeOil = getMaintenanceWorkListOil(auto).get(getMaintenanceWorkListOil(auto).size() - 1);
        }

        return maintenanceWorkLastChangeOil;
    }


    @Transactional
    public MaintenanceWork getMaintenanceWorkLastChangeGRM(Auto auto) {
        MaintenanceWork maintenanceWorkLastChangeGRM;
        if (getMaintenanceWorkListGRM(auto).isEmpty()) {
            maintenanceWorkLastChangeGRM = null;
        } else {
            maintenanceWorkLastChangeGRM = getMaintenanceWorkListGRM(auto).get(getMaintenanceWorkListGRM(auto).size() - 1);
        }

        return maintenanceWorkLastChangeGRM;

    }

    @Transactional
    public List<MaintenanceWork> getMaintenanceWorkListGRM(Auto auto) {
        List<MaintenanceWork> maintenanceWorkListGRM = new ArrayList<>();
        for (MaintenanceWork maintenanceWorkChangeGRM : auto.getMaintenanceWorkList()) {
            if (maintenanceWorkChangeGRM.getNameWork() == NameWork.CHANGE_GRM) {
                maintenanceWorkListGRM.add(maintenanceWorkChangeGRM);
            }
        }
        Collections.sort(maintenanceWorkListGRM, Comparator.comparing(MaintenanceWork::getDateChange));
        return maintenanceWorkListGRM;
    }

    @Transactional
    public void saveOtherWorkAuto(Long id, OtherWork otherWork) {
        Auto auto = autoRepository.findById(id).get();
        List<OtherWork> otherWorksList = auto.getOtherWorksList();
        if (otherWork.getCostWork() == null) {
            otherWork.setCostWork(0);
        }
        if (otherWork.getCostPart() == null) {
            otherWork.setCostPart(0);
        }
        otherWorksList.add(otherWork);
        auto.setId(id);
        auto.addOtherWorkToAuto(otherWork);
        autoRepository.save(auto);
    }

    @Transactional
    public void updateDateEndInsurance(Long id, Auto updatedAuto) {
        Auto auto;
        auto = autoRepository.findById(id).get();
        auto.setInsuranceEndDate(updatedAuto.getInsuranceEndDate());
        auto.setId(id);
        autoRepository.save(auto);
    }


    @Transactional
    public void updateDateCompletionTechnicalInspection(Long id, Auto updatedAuto) {
        Auto auto;
        auto = autoRepository.findById(id).get();
        auto.setDateCompletionTechnicalInspection(updatedAuto.getDateCompletionTechnicalInspection());
        auto.setId(id);
        autoRepository.save(auto);
    }

    @Transactional
    public List<Image> getListPhotosDamage(Auto auto) {
        List<Image> listPhotosDamage = new ArrayList<>();
        for (Image img : auto.getImages()) {
            if (!img.isPreviewImage()) {
                listPhotosDamage.add(img);
                System.out.println(img.isPreviewImage());
            }
        }
        return listPhotosDamage;
    }


}
