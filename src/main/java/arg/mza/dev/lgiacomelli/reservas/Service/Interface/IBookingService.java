package arg.mza.dev.lgiacomelli.reservas.Service.Interface;


import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService {
    List<Booking> getAllBookings();
    List<Booking> getAllActivesBookings();
    List<Booking> getAllBookingsByRoom(Long roomId);



}
