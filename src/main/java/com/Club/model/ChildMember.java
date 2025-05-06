package com.Club.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CHILD")
public class ChildMember extends Member {

    private String guardianName;

    public ChildMember(String name, String email, String phone, LocalDate startDate, LocalDate endDate, BigDecimal price, String guardianName) {
        super(name, email, phone, startDate, endDate, price);
        this.guardianName = guardianName;
    }
}