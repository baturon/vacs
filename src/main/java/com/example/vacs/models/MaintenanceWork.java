package com.example.vacs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MaintenanceWork")
public class MaintenanceWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nameMaintenanceWork")
    private String nameMaintenanceWork;

    @Column(name = "mileageChange")
    private int mileageChange;

    @Column(name = "mileageNextChange")
    private int mileageNextChange;

    @Column(name = "dateChange")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateChange;


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Auto auto;

}



