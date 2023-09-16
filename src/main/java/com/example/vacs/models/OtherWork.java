package com.example.vacs.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "other_work")
public class OtherWork extends Work {

    @Column(name = "name_work")
    private String nameWork;

    @Column(name = "cost_part")
    private int costPart;

    @Column(name = "cost_work")
    private int costWork;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Auto auto;

}


