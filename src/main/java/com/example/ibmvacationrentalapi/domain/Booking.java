package com.example.ibmvacationrentalapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nomeHospede", referencedColumnName = "name")
    private UserProfile userProfile;

    private String dataInicio;
    private String dataFim;
    private Integer quantidadePessoas;
    private String status;
}
