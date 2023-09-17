package com.example.vacs.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Выберите марку")
    @Column(name = "brand")
    private String brand;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Выберите модель")
    @Column(name = "model")
    private String model;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 7, max = 10, message = "Номер должен содержать от 7 до 10 символов ")
    @Column(name = "license_plate")
    private String licensePlate;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4, max = 4, message = "Введите год выпуска 4 символа ")
    @Column(name = "yearOfIssue")
    private String yearOfIssue;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 17, max = 17, message = "Введите VIN-номер 17 символов ")
    @Column(name = "vinNumber")
    private String vinNumber;

    @Column(name = "mileage")
    private int mileage;



//    @OneToOne(fetch = FetchType.LAZY,mappedBy = "auto")
//    private MaintenanceWork maintenanceWorkLastChangeOil;

//    @Column(name = "mileageNextChangeOil")
//    private int mileageNextChangeOil;


//    @Column(name = "mileageNextChangeGRM")
//    private int mileageNextChangeGRM;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auto")
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auto")
    private List<MaintenanceWork> maintenanceWorkList = new ArrayList<>();




    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auto")
    private List<OtherWork> otherWorksList = new ArrayList<>();

    //private Long previewImageId;
    private LocalDateTime dateOfCreated;


    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToAuto(Image image) {
        image.setAuto(this);
        images.add(image);
    }

    public void addMaintenanceWorkToAuto(MaintenanceWork maintenanceWork) {
        maintenanceWork.setAuto(this);
        maintenanceWorkList.add(maintenanceWork);
    }

    public void addOtherWorkToAuto(OtherWork otherWork) {
        otherWork.setAuto(this);
        otherWorksList.add(otherWork);
    }

    public List<OtherWork> getOtherWorksList() {

        Collections.sort(otherWorksList, Comparator.comparing(OtherWork::getDateChange));
        return otherWorksList;
    }
}
