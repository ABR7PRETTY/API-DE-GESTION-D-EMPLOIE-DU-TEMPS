package org.abr7.back.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "enseignant")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "telephone",nullable = false, unique = true)
    private String telephone;

    @Column(name = "numeroM", nullable = false, unique = true)
    private Integer numeroM;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

}