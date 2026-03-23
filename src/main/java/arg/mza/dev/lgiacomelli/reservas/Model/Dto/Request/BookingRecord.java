package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRecord(

        @FutureOrPresent(message = "The check in must be in the present or in the future")
        LocalDate checkInDate,

        @Future(message = "The ckeck out must be in the future")
        LocalDate checkOutDate,

        @NotNull
        Long clienteId,

        @NotNull
        Long roomId

) {
}
