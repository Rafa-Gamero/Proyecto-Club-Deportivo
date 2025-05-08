package com.Club.model;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String schedule;

    @OneToMany(mappedBy = "discipline")
    private List<MemberDiscipline> memberDisciplines;
}