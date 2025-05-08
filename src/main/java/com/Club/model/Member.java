package com.Club.model;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type")
@Getter
@Setter
@RequiredArgsConstructor
public abstract class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

    @ManyToOne
    private Trainer trainer;
}
