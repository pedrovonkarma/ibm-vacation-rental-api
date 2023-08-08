package com.example.ibmvacationrentalapi.service;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.BookingDto;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.repository.BookingRepository;
import com.example.ibmvacationrentalapi.repository.UserRepository;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingDto insert(BookingDto obj) {
        if (obj.getNomeHospede() == null) {
            throw new ObjectNotFoundException("O nome do hóspede é obrigatório!");
        }

        UserProfile userProfile = userRepository.findByName(obj.getNomeHospede())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));

        Booking booking = new Booking(null, userProfile, obj.getDataInicio(), obj.getDataFim(), obj.getQuantidadePessoas(), "CONFIRMADA");
        booking.setUserProfile(userProfile);

        booking = bookingRepository.save(booking);

        return new BookingDto(booking);
    }

    public List<BookingDto> findAll() {
        List<Booking> list = bookingRepository.findAll();
        List<BookingDto> dtoList = new ArrayList<>();
        for (Booking booking : list) {
            BookingDto dto = new BookingDto(booking);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BookingDto findById(Integer id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
        BookingDto bookingDto = new BookingDto(booking);
        return bookingDto;
    }

    public BookingDto update(Integer id, BookingDto obj) {

        UserProfile userProfile = userRepository.findByName(obj.getNomeHospede())
                .orElseThrow(() -> new ObjectNotFoundException("Pessoa não cadastrada"));
        Booking newBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada"));
        newBooking.setUserProfile(userProfile);
        newBooking.setDataInicio(obj.getDataInicio());
        newBooking.setDataFim(obj.getDataFim());
        newBooking.setQuantidadePessoas(obj.getQuantidadePessoas());
        newBooking.setStatus(obj.getStatus());
        bookingRepository.save(newBooking);
        return new BookingDto(newBooking);

    }

    public BookingDto delete(Integer id) {
        Booking newBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada"));
        newBooking.setStatus("CANCELADA");
        bookingRepository.save(newBooking);
        return new BookingDto(newBooking);
    }

    public Booking fromDto(BookingDto objDto){
        UserProfile userProfile = userRepository.findByName(objDto.getNomeHospede())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
        return new Booking(objDto.getId(), userProfile, objDto.getDataInicio(), objDto.getDataFim(), objDto.getQuantidadePessoas(), objDto.getStatus());
    }

}
