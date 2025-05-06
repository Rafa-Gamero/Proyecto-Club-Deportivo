package com.Club.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
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

    public int getAge() {
        return startDate != null ? LocalDate.now().getYear() - startDate.getYear() : 0;
    }
    public Member(String name, String email, String phone, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }


}
