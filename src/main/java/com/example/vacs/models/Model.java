package com.example.vacs.models;


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



}
