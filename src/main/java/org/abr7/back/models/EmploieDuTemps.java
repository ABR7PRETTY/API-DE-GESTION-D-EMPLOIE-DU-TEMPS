package org.abr7.back.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "emploieDuTemps")
public class EmploieDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "salle", nullable = false, unique = true)
    private String code;

    @Column(name = "heure_debut", nullable = false, unique = true)
    private String heure_debut;

    @Column(name = "heure_fin", nullable = false, unique = true)
    private String heure_fin;

    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant;

}
