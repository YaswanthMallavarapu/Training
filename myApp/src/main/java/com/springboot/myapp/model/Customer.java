package com.springboot.myapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String city;
}
