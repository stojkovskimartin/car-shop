package com.example.emtseminarska.service.impl;

import com.example.emtseminarska.models.CustomUserPrincipal;
import com.example.emtseminarska.models.User;
import com.example.emtseminarska.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var users = userRepository.findAll();
        User user = null;
        for (var u: users) {
            if(u.getFirstName().equals(s)) {
                user = u;
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserPrincipal(user);
    }
}
