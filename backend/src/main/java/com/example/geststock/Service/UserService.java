package com.example.geststock.Service;

import com.example.geststock.Model.UserModel;
import com.example.geststock.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public UserModel add(UserModel user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Ce nom d'utilisateur existe déjà");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Cet email existe déjà");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserModel update(int id, UserModel user) {
        UserModel existingUser = getById(id);

        if (!existingUser.getUsername().equals(user.getUsername()) && 
            userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Ce nom d'utilisateur existe déjà");
        }

        if (!existingUser.getEmail().equals(user.getEmail()) && 
            userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Cet email existe déjà");
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setStatut(user.isStatut());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        return userRepository.save(existingUser);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
} 