package com.example.customers1.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
