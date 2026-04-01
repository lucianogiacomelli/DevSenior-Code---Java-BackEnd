package arg.mza.dev.lgiacomelli.reservas.Service.Implementation;

import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceNotFoundException;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import arg.mza.dev.lgiacomelli.reservas.Repository.BookingRepository;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IBookingService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BookingService implements IBookingService {


    private BookingRepository bookingRepository;

    public BookingService (BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }
    @Override
    @Transactional (readOnly = true)
    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        if(bookings.isEmpty()){
            throw new ResourceNotFoundException("There are no bookings in the system");
        }
        return bookings;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getAllActivesBookings(){
        List <Booking> bookings = bookingRepository.getActivesBookings();
        if(bookings.isEmpty()){
            throw new ResourceNotFoundException("There are no active and/or future bookings in the system");
        }
        return bookings;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getAllBookingsByRoom(Long roomId){
        List <Booking> bookings = bookingRepository.getActivesBookingsByRoom(roomId);
        if(bookings.isEmpty()){
            throw new ResourceNotFoundException("There are no active and/or future bookings in the system for the ID room:"+roomId);
        }
        return bookings;
    }





}
