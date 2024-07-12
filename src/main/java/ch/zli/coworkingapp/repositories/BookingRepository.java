package ch.zli.coworkingapp.repositories;

import ch.zli.coworkingapp.entities.Booking;
import ch.zli.coworkingapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
