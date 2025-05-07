package com.Club.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> getUserProfile() {
        return ResponseEntity.ok("Here is the user profile");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> updateUserProfile() {
        return ResponseEntity.ok("User profile updated");
    }
}
