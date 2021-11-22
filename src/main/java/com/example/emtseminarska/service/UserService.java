package com.example.emtseminarska.service;

import com.example.emtseminarska.models.User;
import com.example.emtseminarska.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User findByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user :     users) {
            if (user.getFirstName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
