package arg.mza.dev.lgiacomelli.reservas.Mapper;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.RoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;

public class RoomMapper {

    public static RoomResponse toResponse(Room r) {
        return new RoomResponse(
                r.getId(),
                r.getCreatedAt(),
                r.getDeletedAt(),
                r.getEstado(),
                r.getRoomNumber(),
                r.getFloorNumber(),
                r.getRoomType()
        );
    }
}