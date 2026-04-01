package arg.mza.dev.lgiacomelli.reservas.Controller;

import arg.mza.dev.lgiacomelli.reservas.Mapper.BookingMapper;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.BookingResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    private IBookingService bookingService;
    @GetMapping("/")
    public ResponseEntity<?> getAllBookings(){
        List<Booking> bookingList = bookingService.getAllBookings();
        List <BookingResponse> response = bookingList.stream().map(BookingMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/actives-bookings")
    public ResponseEntity<?> getAllActivesBookings(){
        List<Booking> bookingList = bookingService.getAllActivesBookings();
        List <BookingResponse> response = bookingList.stream().map(BookingMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getAllBookingsByRoom(@PathVariable Long roomId){
        List<Booking> bookingList = bookingService.getAllBookingsByRoom(roomId);
        List <BookingResponse> response = bookingList.stream().map(BookingMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    




}
