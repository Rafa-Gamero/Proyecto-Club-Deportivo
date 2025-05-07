package com.Club.service;



import com.Club.model.AppUser;
import com.Club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Buscamos el usuario por su nombre de usuario
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retornamos un UserDetails con la información del usuario (nombre y roles)
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRoles().toArray(new String[0])) // Asumimos que AppUser tiene un campo roles
                .build();
    }
}