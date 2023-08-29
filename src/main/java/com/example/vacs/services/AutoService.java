package com.example.vacs.services;

import com.example.vacs.models.Auto;
import com.example.vacs.models.Image;
import com.example.vacs.repositories.AutoRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void saveAuto(Auto auto, MultipartFile[] files) throws IOException {
        int count = 0;
        List<Image> image= new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                image.add(toImageEntity(file));
                auto.addImageToAuto(image.get(count));
                count ++;
            }
        }

//        Image image1;
//        Image image2;
//        Image image3;
//        if (file1.getSize() != 0) {
//            image1 = toImageEntity(file1);
//            image1.setPreviewImage(true);
//            auto.addImageToAuto(image1);
//        }
//
//        if (file2.getSize() != 0) {
//            image2 = toImageEntity(file2);
//            auto.addImageToAuto(image2);
//        }
//        if (file3.getSize() != 0) {
//            image3 = toImageEntity(file3);
//            auto.addImageToAuto(image3);
//        }

//        log.info("Saving new {}", auto);
        //Auto autoFromDb = autoRepository.save(auto);
        //autoFromDb.setPreviewImageId(autoFromDb.getImages().get(0).getId());
        autoRepository.save(auto);

    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
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


}
