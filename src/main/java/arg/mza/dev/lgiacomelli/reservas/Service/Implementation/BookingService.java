package arg.mza.dev.lgiacomelli.reservas.Service.Implementation;

import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceInvalidException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceNotFoundException;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.BookingRecord;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;
import arg.mza.dev.lgiacomelli.reservas.Repository.BookingRepository;
import arg.mza.dev.lgiacomelli.reservas.Repository.ClientRepository;
import arg.mza.dev.lgiacomelli.reservas.Repository.RoomRepository;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IBookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService implements IBookingService {


    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private RoomRepository roomRepository;

    public BookingService (BookingRepository bookingRepository, ClientRepository clientRepository, RoomRepository roomRepository){
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
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

    @Override
    @Transactional
    public Booking createBooking(BookingRecord bookingRecord){

        Optional<Client> optionalClient = clientRepository.findByDni(bookingRecord.clientDni());
        if(optionalClient.isEmpty()){
            throw new ResourceNotFoundException("The client with DNI:"+bookingRecord.clientDni()+" does not exist.");
        }
        Client client = optionalClient.get();
        if(!client.getEstado()){
            throw new ResourceInvalidException("The client with DNI: "+bookingRecord.clientDni()+" is deactivated.");
        }

        Optional<Room> optionalRoom = roomRepository.findById(bookingRecord.roomId());
        if(optionalRoom.isEmpty()){
            throw new ResourceNotFoundException("The room with ID:"+bookingRecord.roomId()+" does not exist.");
        }
        Room room = optionalRoom.get();
        if(!room.getEstado()){
            throw new ResourceInvalidException("The room with ID: "+bookingRecord.roomId()+" is deactivated.");
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setClient(client);
        booking.setCheckInDate(bookingRecord.checkInDate());
        booking.setCheckOutDate(bookingRecord.checkOutDate());

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking deleteBooking (Long bookingId){
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if(bookingOptional.isEmpty()){
            throw new ResourceNotFoundException("The booking with ID:"+bookingId+"does not exist.");
        }
        Booking booking = bookingOptional.get();
        if(!booking.getEstado()){
            throw new ResourceInvalidException("The booking with ID:"+bookingId+"is deactivated.");
        }
        booking.setEstado(false);
        booking.setDeletedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }





}
