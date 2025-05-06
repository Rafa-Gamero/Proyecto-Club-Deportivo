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
@DiscriminatorValue("ADULT")
public class AdultMember extends Member {

    private String occupation;

    public AdultMember(String name, String email, String phone, LocalDate startDate, LocalDate endDate, BigDecimal price, String occupation) {
        super(name, email, phone, startDate, endDate, price);
        this.occupation = occupation;
    }
}