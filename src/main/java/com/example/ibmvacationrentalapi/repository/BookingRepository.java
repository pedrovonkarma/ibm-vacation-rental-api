package com.example.ibmvacationrentalapi.repository;

import com.example.ibmvacationrentalapi.domain.Booking;
import com.example.ibmvacationrentalapi.dto.BookingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
