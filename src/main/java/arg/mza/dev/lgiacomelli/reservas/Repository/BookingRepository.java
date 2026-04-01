package arg.mza.dev.lgiacomelli.reservas.Repository;


import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
    SELECT COUNT(b)>0
    FROM Booking b
    WHERE b.room.id = :roomId
    AND b.checkOutDate > :checkIn
    AND b.checkInDate < :checkOut
    AND b.estado = true
""")
    boolean existsOverlappingBooking (@Param("roomId") Long roomId,
                                      @Param("checkIn") LocalDate checkIn,
                                      @Param("checkOut") LocalDate checkOut);

    @Query("""
    SELECT b
    FROM Booking b
    WHERE b.checkOutDate > CURRENT_DATE
    AND b.checkInDate <= CURRENT_DATE
    AND b.estado = true
""")
    List<Booking> getActivesBookings();

    @Query("""
    SELECT b
    FROM Booking b
    WHERE b.checkOutDate > CURRENT_DATE
    AND b.checkInDate <= CURRENT_DATE
    AND b.room.id = : roomId
    AND b.estado = true
""")
    List<Booking> getActivesBookingsByRoom(@Param("roomId") Long roomId);

}
