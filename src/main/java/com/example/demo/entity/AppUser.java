package com.example.demo.entity;
import jakarta.persistence.Entity;
import lombok.*;
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private Long id;
    private String email;
    private String password;
    private String role;
    private Boolean active;
}
