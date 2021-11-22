package com.example.emtseminarska.service;


import com.example.emtseminarska.models.CustomUserPrincipal;

public interface AuthService {
    CustomUserPrincipal getCurrentUser();
    Long getCurrentUserId();
}
