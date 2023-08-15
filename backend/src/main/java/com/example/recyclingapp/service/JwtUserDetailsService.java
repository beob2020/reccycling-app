package com.example.recyclingapp.service;


import com.example.recyclingapp.model.User;
import com.example.recyclingapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public User save(User user) {
        User newUser = User.builder()
                .password(bcryptEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return userRepository.save(newUser);
    }
}