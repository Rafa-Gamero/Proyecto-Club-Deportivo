package com.Club.model;



import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class AdultMember extends Member {
    private String occupation;
}