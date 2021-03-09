package com.example.geststock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    private boolean statut;
    
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_ajout;
    
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_modification;
}

// Enum pour les rôles
enum UserRole {
    ADMIN,
    USER,
    VENDEUR,
    MANAGER,
    SUPPORT
} 