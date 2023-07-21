package com.example.vacs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 20, message = "Название должно содержать от 2 до 20 символов ")
    @Column(name = "brand")
    private String brand;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 20, message = "Название должно содержать от 2 до 20 символов ")
    @Column(name = "model")
    private String model;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 7, max = 10, message = "Номер должен содержать от 7 до 10 символов ")
    @Column(name = "license_plate")
    private String licensePlate;

    //    @NotEmpty(message = "Поле не должно быть пустым")
//    @Size(min = 4, message ="Введите год выпуска минимум 4 символа " )
//    //@Size(max = 4, message ="Введите год выпуска максимум 4 символа " )
   // private int yearOfIssue;


    public Auto() {

    }
}
