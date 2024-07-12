package ch.zli.coworkingapp.repositories;

import ch.zli.coworkingapp.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
