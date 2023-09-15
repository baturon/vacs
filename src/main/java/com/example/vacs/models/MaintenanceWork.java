package com.example.vacs.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "maintenance_work")
public class MaintenanceWork extends Work {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_work")
    @Enumerated(EnumType.STRING)
    private NameWork nameWork;

    @Column(name = "mileageNextChange")
    private int mileageNextChange;

    @Column(name = "nextReplacementIn")
    private int nextReplacementIn;



    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Auto auto;

}
