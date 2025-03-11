package com.learning.security.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String pwd;
    @Column(name = "role")
    private String role;


}
