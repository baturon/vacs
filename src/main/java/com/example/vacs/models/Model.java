package com.example.vacs.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "model")
public class Model {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;


//    @ManyToOne
//    public Firm firm;

}
