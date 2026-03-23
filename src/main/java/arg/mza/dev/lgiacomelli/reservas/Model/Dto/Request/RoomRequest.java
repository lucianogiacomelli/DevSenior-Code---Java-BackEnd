package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request;

import arg.mza.dev.lgiacomelli.reservas.Model.Entity.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomRequest(

        @Positive(message = "The room number must be positive")
        @NotNull(message = "The room number must not be null")
        Integer roomNumber,

        @Positive(message = "The floor number must be positive")
        @NotNull(message = "The floor number must not be null")
        Integer floorNumber,

        @NotNull(message = "The type of room must not be null")
        RoomType roomType
) {
}
