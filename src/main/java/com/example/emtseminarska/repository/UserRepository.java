package com.example.emtseminarska.repository;

import com.example.emtseminarska.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByConfirmationToken(String confirmationToken);

    User findByEmail(String email);
}
