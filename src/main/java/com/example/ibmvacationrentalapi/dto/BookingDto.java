package com.example.ibmvacationrentalapi.dto;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.domain.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDto implements Serializable {

    private Integer id;
    private String nomeHospede;
    private String dataInicio;
    private String dataFim;
    private Integer quantidadePessoas;
    private String status;

    public BookingDto(Booking obj) {
        id = obj.getId();
        nomeHospede = obj.getUserProfile().getName();
        dataInicio = obj.getDataInicio();
        dataFim = obj.getDataFim();
        quantidadePessoas = obj.getQuantidadePessoas();
        status = obj.getStatus();
    }
}
