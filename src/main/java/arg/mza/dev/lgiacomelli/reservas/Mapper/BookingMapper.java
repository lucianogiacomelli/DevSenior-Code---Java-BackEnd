package arg.mza.dev.lgiacomelli.reservas.Mapper;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.BookingResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Booking;

public class BookingMapper {

    public static BookingResponse toResponse(Booking b) {
        return new BookingResponse(
                b.getId(),
                b.getCreatedAt(),
                b.getDeletedAt(),
                b.getEstado(),
                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getClient() != null ? b.getClient().getId() : null,
                b.getRoom() != null ? b.getRoom().getId() : null
        );
    }
}