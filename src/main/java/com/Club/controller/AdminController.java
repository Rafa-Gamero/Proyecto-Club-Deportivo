package com.Club.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard");
    }

    @PostMapping("/create-report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createReport() {
        return ResponseEntity.ok("Report created successfully");
    }
}
