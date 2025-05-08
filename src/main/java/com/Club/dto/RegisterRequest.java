package com.Club.dto;



import com.Club.model.UserRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private UserRole role;
}
