package com.maarimo.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospedes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

}