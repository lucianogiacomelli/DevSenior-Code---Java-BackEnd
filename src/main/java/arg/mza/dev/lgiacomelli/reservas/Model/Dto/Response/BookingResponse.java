package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingResponse(Long id, LocalDateTime createdAt, LocalDateTime deletedAt,
                              Boolean condition, LocalDate checkInDate,
                              LocalDate checkOutDate, Long client, Long room) {
}
