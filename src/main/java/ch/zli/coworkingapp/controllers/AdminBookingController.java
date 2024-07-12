package ch.zli.coworkingapp.controllers;

import ch.zli.coworkingapp.entities.Booking;
import ch.zli.coworkingapp.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            Booking existingBooking = booking.get();
            existingBooking.setDate(updatedBooking.getDate());
            existingBooking.setHalfDay(updatedBooking.getHalfDay());
            existingBooking.setStatus(updatedBooking.getStatus());
            bookingRepository.save(existingBooking);
            return ResponseEntity.ok("Booking updated successfully");
        }
        return ResponseEntity.status(404).body("Booking not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
