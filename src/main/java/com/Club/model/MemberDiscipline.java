package com.Club.model;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MemberDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Discipline discipline;

    private LocalDate enrollmentDate;
}
