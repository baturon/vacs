package com.example.vacs.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Work {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
//    private Long id;


    @Column(name = "mileageChange")
    private int mileageChange;

    @Column(name = "mileageNextChange")
    private int mileageNextChange;

    @Column(name = "nextReplacementIn")
    private int nextReplacementIn;

    @Column(name = "dateChange")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateChange;


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Auto auto;


}



