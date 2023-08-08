package com.example.ibmvacationrentalapi.controller;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.dto.BookingDto;
import com.example.ibmvacationrentalapi.service.BookingService;
import com.example.ibmvacationrentalapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto requestDto) {
        BookingDto responseDto = bookingService.insert(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> findAll(){
        List<BookingDto> list = bookingService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findById(@PathVariable Integer id){
        BookingDto booking = bookingService.findById(id);
        return ResponseEntity.ok().body(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> update(@PathVariable Integer id, @RequestBody BookingDto obj) {
        BookingDto newBooking = bookingService.update(id, obj);
        return ResponseEntity.ok().body(newBooking);
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<BookingDto> delete(@PathVariable Integer id){
        BookingDto canceledBooking = bookingService.delete(id);
        return ResponseEntity.ok().body(canceledBooking);
    }
}
