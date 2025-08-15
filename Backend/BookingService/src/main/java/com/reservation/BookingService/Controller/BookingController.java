package com.reservation.BookingService.Controller;


import com.reservation.BookingService.Model.DAO.service.BookingService;
import com.reservation.BookingService.Model.DTO.BookingRequestDto;
import com.reservation.BookingService.Model.DTO.BookingResponseDto;
import com.reservation.BookingService.Model.DTO.CancellationResponseDto;
import com.reservation.BookingService.Model.DTO.PassengerResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto bookingRequest,
            @RequestHeader("X-User-Id") Long userId) {
        BookingResponseDto booking = bookingService.createBooking(bookingRequest, userId);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }


    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<CancellationResponseDto> cancelBooking(
            @PathVariable Long bookingId,
            @RequestHeader("X-User-Id") Long userId) {
        CancellationResponseDto cancellation = bookingService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(cancellation);
    }


    @GetMapping("/admin/all")
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookingResponseDto>> getUserBookings(
            @RequestHeader("X-User-Id") Long userId) {
        List<BookingResponseDto> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/upcoming")
    public ResponseEntity<List<BookingResponseDto>> getUpcomingJourneys(
            @RequestHeader("X-User-Id") Long userId) {
        List<BookingResponseDto> bookings = bookingService.getUpcomingJourneys(userId);
        return ResponseEntity.ok(bookings);
    }


    @GetMapping("/check-schedule/{scheduleId}")
    public ResponseEntity<Boolean> hasActiveBookings(@PathVariable Long scheduleId) {
        boolean hasActiveBookings = bookingService.hasActiveBookingsByScheduleId(scheduleId);
        return ResponseEntity.ok(hasActiveBookings);
    }

    //14-08-2025
    @GetMapping("/schedule/{scheduleId}/passengers")
    public ResponseEntity<List<PassengerResponseDto>> getPassengersBySchedule(@PathVariable Long scheduleId) {
        List<PassengerResponseDto> passengers = bookingService.getPassengersBySchedule(scheduleId);
        return ResponseEntity.ok(passengers);
    }


}
