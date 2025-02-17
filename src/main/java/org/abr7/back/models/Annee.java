package org.abr7.back.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "annee")
public class Annee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "debut", nullable = false)
    private String debut;

    @Column(name = "fin", nullable = false)
    private String fin;

}