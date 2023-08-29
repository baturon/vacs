package com.example.vacs.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "firm")
public class Firm  {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    //    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "firm")
//    @JoinTable(name="firm_models",
//            joinColumns = @JoinColumn(name = "firmId",referencedColumnName="id"),
//            inverseJoinColumns = @JoinColumn(name = "modelsId",referencedColumnName="id"))
//
    @OneToMany(fetch = FetchType.EAGER)
    public List<Model> models = new ArrayList<>();

}
