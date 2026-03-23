package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response;

import arg.mza.dev.lgiacomelli.reservas.Model.Entity.RoomType;

import java.time.LocalDateTime;

public record RoomResponse(Long id, LocalDateTime createdAt, LocalDateTime deletedAt,
                           Boolean condition, Integer roomNumber, Integer floorNumber,
                           RoomType roomType) {
}
