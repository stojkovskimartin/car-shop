package com.example.emtseminarska.service.impl;

import com.example.emtseminarska.models.CustomUserPrincipal;
import com.example.emtseminarska.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public CustomUserPrincipal getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserPrincipal){
            return (CustomUserPrincipal)principal;
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        var currentUser = getCurrentUser();
        if(currentUser == null){
            return -1L;
        }

        return currentUser.getUserId();
    }
}