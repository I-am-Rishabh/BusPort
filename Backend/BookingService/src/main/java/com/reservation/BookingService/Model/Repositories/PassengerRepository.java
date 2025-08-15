package com.reservation.BookingService.Model.Repositories;


import com.reservation.BookingService.Model.Entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByBookingId(Long bookingId);
    List<Passenger> findByBookingIdIn(List<Long> bookingIds);
    @Query("SELECT p.seatNumber FROM Passenger p WHERE p.booking.scheduleId = :scheduleId AND p.booking.status = 'CONFIRMED'")
    List<Integer> findOccupiedSeatsByScheduleId(@Param("scheduleId") Long scheduleId);
}