package com.example.recyclingapp.service;

import com.example.recyclingapp.exceptions.UserNotFoundException;
import com.example.recyclingapp.model.User;
import com.example.recyclingapp.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id: " + id + " was not found."));
    }

    public User updateUser(Long id, User user) {
        User oldUser = getUserById(id);

        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setAddress(user.getAddress());
        oldUser.setName(user.getName());
        oldUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(oldUser);
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
