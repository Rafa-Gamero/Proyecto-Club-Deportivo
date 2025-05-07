package com.Club.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;

    public Trainer() {
    }

    public Trainer(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }


    @OneToMany(mappedBy = "trainer")
    private List<Discipline> disciplines;

}