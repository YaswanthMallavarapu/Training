package com.springboot.myapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="documents")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileImage;

    @ManyToOne
    private Customer customer;
}
