package com.example.ibmvacationrentalapi.service;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.BookingDto;
import com.example.ibmvacationrentalapi.repository.BookingRepository;
import com.example.ibmvacationrentalapi.repository.UserRepository;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingService bookingService;

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
    public void insertBookingTest(){

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDto insertedBooking = bookingService.insert(bookingDto);

        assertNotNull(insertedBooking);
        assertEquals(bookingDto.getNomeHospede(), insertedBooking.getNomeHospede());
        assertEquals(bookingDto.getDataInicio(), insertedBooking.getDataInicio());
        assertEquals(bookingDto.getDataFim(), insertedBooking.getDataFim());
        assertEquals(bookingDto.getQuantidadePessoas(), insertedBooking.getQuantidadePessoas());
        assertEquals(bookingDto.getStatus(), insertedBooking.getStatus());
        assertNull(insertedBooking.getId());

        verify(bookingRepository, times(1)).save(any(Booking.class));

    }

    @Test
    public void findAllBookingsTest() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookingList);

        List<BookingDto> bookingDtoList = bookingService.findAll();

        assertNotNull(bookingDtoList);
        assertEquals(bookingList.size(), bookingDtoList.size());

        BookingDto convertedDto = bookingDtoList.get(0);

        assertEquals(booking.getId(), convertedDto.getId());
        assertEquals(booking.getUserProfile().getName(), convertedDto.getNomeHospede());
        assertEquals(booking.getDataInicio(), convertedDto.getDataInicio());
        assertEquals(booking.getDataFim(), convertedDto.getDataFim());
        assertEquals(booking.getQuantidadePessoas(), convertedDto.getQuantidadePessoas());
        assertEquals(booking.getStatus(), convertedDto.getStatus());

        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void findBookingByIdTest() {

        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(booking));

        BookingDto foundBookingDto = bookingService.findById(1);

        assertNotNull(foundBookingDto);
        assertEquals(booking.getId(), foundBookingDto.getId());
        assertEquals(booking.getUserProfile().getName(), foundBookingDto.getNomeHospede());
        assertEquals(booking.getDataInicio(), foundBookingDto.getDataInicio());
        assertEquals(booking.getDataFim(), foundBookingDto.getDataFim());
        assertEquals(booking.getQuantidadePessoas(), foundBookingDto.getQuantidadePessoas());
        assertEquals(booking.getStatus(), foundBookingDto.getStatus());

        verify(bookingRepository, times(1)).findById(1);
    }

    @Test
    public void findNonExistentBookingByIdTest() {
        when(bookingRepository.findById(999)).thenReturn(java.util.Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> bookingService.findById(999));
        verify(bookingRepository, times(1)).findById(999);
    }

    @Test
    public void updateBookingTest() {
        BookingDto updatedBookingDto = new BookingDto(1, "jane", "2023-06-01", "2023-12-31", 3, "UPDATED");

        when(userRepository.findByName("jane")).thenReturn(java.util.Optional.ofNullable(userProfile));
        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDto resultBookingDto = bookingService.update(1, updatedBookingDto);

        assertNotNull(resultBookingDto);
        assertEquals(updatedBookingDto.getNomeHospede(), resultBookingDto.getNomeHospede());
        assertEquals(updatedBookingDto.getDataInicio(), resultBookingDto.getDataInicio());
        assertEquals(updatedBookingDto.getDataFim(), resultBookingDto.getDataFim());
        assertEquals(updatedBookingDto.getQuantidadePessoas(), resultBookingDto.getQuantidadePessoas());
        assertEquals(updatedBookingDto.getStatus(), resultBookingDto.getStatus());

        verify(userRepository, times(1)).findByName("jane");
        verify(bookingRepository, times(1)).findById(1);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void deleteBookingTest() {
        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(booking));

        BookingDto deletedBookingDto = bookingService.delete(1);

        assertNotNull(deletedBookingDto);
        assertEquals("CANCELADA", deletedBookingDto.getStatus());

        verify(bookingRepository, times(1)).findById(1);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void deleteNonExistentBookingTest() {
        when(bookingRepository.findById(999)).thenReturn(java.util.Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> bookingService.delete(999));
        verify(bookingRepository, times(1)).findById(999);
    }

}
