package com.example.geststock.Repository;

import com.example.geststock.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 