package com.example.ibmvacationrentalapi.controller;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.BookingDto;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.service.BookingService;
import com.example.ibmvacationrentalapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private BookingDto bookingDto;

    private Booking booking;

    private UserProfile userProfile;


    @BeforeEach
    public void setup(){
        userProfile = new UserProfile(1, "john", "john@gmail.com", "21912345678");
        booking = new Booking(1, userProfile, "2023-05-22", "2023-12-22", 4, null);
        bookingDto = new BookingDto(1, "john", "2023-05-22", "2023-12-22", 4, null);
    }

    @Test
    public void testCreateBooking() throws Exception {
        when(bookingService.insert(any(BookingDto.class))).thenReturn(bookingDto);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookingDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeHospede").value("john"))
                .andExpect(jsonPath("$.dataInicio").value("2023-05-22"))
                .andExpect(jsonPath("$.dataFim").value("2023-12-22"))
                .andExpect(jsonPath("$.quantidadePessoas").value(4))
                .andExpect(jsonPath("$.status").isEmpty());

        verify(bookingService, times(1)).insert(bookingDto);
    }

    @Test
    public void testFindAllBookings() throws Exception {
        List<BookingDto> bookingDtoList = Collections.singletonList(bookingDto);
        when(bookingService.findAll()).thenReturn(bookingDtoList);

        mockMvc.perform(get("/reservas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeHospede").value("john"))
                .andExpect(jsonPath("$[0].dataInicio").value("2023-05-22"))
                .andExpect(jsonPath("$[0].dataFim").value("2023-12-22"))
                .andExpect(jsonPath("$[0].quantidadePessoas").value(4))
                .andExpect(jsonPath("$[0].status").value("CONFIRMADA"));

        verify(bookingService, times(1)).findAll();
    }

    @Test
    public void testFindBookingById() throws Exception {
        when(bookingService.findById(1)).thenReturn(bookingDto);

        mockMvc.perform(get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeHospede").value("john"))
                .andExpect(jsonPath("$.dataInicio").value("2023-05-22"))
                .andExpect(jsonPath("$.dataFim").value("2023-12-22"))
                .andExpect(jsonPath("$.quantidadePessoas").value(4))
                .andExpect(jsonPath("$.status").value("CONFIRMADA"));

        verify(bookingService, times(1)).findById(1);
    }

    @Test
    public void testUpdateBooking() throws Exception {
        BookingDto updatedBooking = new BookingDto(1, "john", "2023-05-22", "2023-12-22", 5, "PENDENTE");


        when(bookingService.update(1, updatedBooking)).thenReturn(updatedBooking);

        mockMvc.perform(put("/reservas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookingDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeHospede").value("john"))
                .andExpect(jsonPath("$.dataInicio").value("2023-05-22"))
                .andExpect(jsonPath("$.dataFim").value("2023-12-22"))
                .andExpect(jsonPath("$.quantidadePessoas").value(5))
                .andExpect(jsonPath("$.status").value("PENDENTE"));

        verify(bookingService, times(1)).update(1, bookingDto);
    }

    @Test
    public void testCancelBooking() throws Exception {
        when(bookingService.delete(1)).thenReturn(bookingDto);

        mockMvc.perform(delete("/reservas/1/cancelar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeHospede").value("john"))
                .andExpect(jsonPath("$.dataInicio").value("2023-05-22"))
                .andExpect(jsonPath("$.dataFim").value("2023-12-22"))
                .andExpect(jsonPath("$.quantidadePessoas").value(4))
                .andExpect(jsonPath("$.status").value("CANCELADA"));

        verify(bookingService, times(1)).delete(1);
    }

    // convert obj into JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
