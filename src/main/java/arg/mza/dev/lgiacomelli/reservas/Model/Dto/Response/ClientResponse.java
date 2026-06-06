package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response;

import java.time.LocalDateTime;

public record ClientResponse(Long id, LocalDateTime createdAt, LocalDateTime deletedAt,
                             Boolean condition, String firstName, String lastName,
                             String phone, String email, String DNI) {
}
