package com.Club.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "member_discipline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Use Lazy fetching
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // Use Lazy fetching
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    // Consider adding a constructor that takes Member and Discipline
    public MemberDiscipline(Member member, Discipline discipline) {
        this.member = member;
        this.discipline = discipline;
    }
}
