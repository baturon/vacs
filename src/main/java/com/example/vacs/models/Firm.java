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


    @OneToMany(fetch = FetchType.EAGER)

    public List<Model> models = new ArrayList<>();

}
