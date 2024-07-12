package ch.zli.coworkingapp.controllers;

import ch.zli.coworkingapp.entities.Booking;
import ch.zli.coworkingapp.entities.User;
import ch.zli.coworkingapp.repositories.BookingRepository;
import ch.zli.coworkingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();

        booking.setUser(user);
        bookingRepository.save(booking);

        return ResponseEntity.ok("Booking created successfully");
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<Booking>> getMyBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();

        List<Booking> bookings = bookingRepository.findByUser(user);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent() && booking.get().getUser().getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Booking cancelled successfully");
        }
        return ResponseEntity.status(403).body("Unauthorized");
    }
}
