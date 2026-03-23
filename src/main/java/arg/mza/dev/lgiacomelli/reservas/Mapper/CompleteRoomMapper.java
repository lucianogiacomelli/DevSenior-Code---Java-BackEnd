package arg.mza.dev.lgiacomelli.reservas.Mapper;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.BookingResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.CompleteRoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;

import java.util.List;
import java.util.stream.Collectors;

public class CompleteRoomMapper {

    public static CompleteRoomResponse toResponse(Room r, List<BookingResponse> bookings) {



        return new CompleteRoomResponse(
                r.getId(),
                r.getCreatedAt(),
                r.getDeletedAt(),
                r.getEstado(),
                r.getRoomNumber(),
                r.getFloorNumber(),
                r.getRoomType(),
                bookings
        );
    }
}