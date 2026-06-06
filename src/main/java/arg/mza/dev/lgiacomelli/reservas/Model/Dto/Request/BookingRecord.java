package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookingRecord(

        @FutureOrPresent(message = "The check in must be in the present or in the future")
        LocalDate checkInDate,

        @Future(message = "The ckeck out must be in the future")
        LocalDate checkOutDate,

        @NotNull(message = "The DNI can not be null")
        @Size(min = 6, message = "The DNI must be at least 6 digits")
        Integer clientDni,

        @NotNull
        Long roomId

) {
}
