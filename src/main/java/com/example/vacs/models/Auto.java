package com.example.vacs.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "brand")
    private String brand;


    @NotEmpty
    @Column(name = "model")
    private String model;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 4, message = "Номер должен содержать от 4 до 10 символов ")
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

    @Column(name = "days_reminder_change_mileage")
    private int daysReminderChangeMileage;

    @Column(name = "date_change_mileage")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateChangeMileage;


    @Column(name = "insurance_end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate insuranceEndDate;

    @Column(name = "date_completion_technical_inspection")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateCompletionTechnicalInspection;


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

    protected boolean canEqual(final Object other) {
        return other instanceof Auto;
    }




}
