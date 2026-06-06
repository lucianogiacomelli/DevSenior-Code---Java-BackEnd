package arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request;

import jakarta.validation.constraints.*;

public record ClientRequest(

        @NotEmpty(message = "The first name can not be empty")
        String firstName,

        @NotEmpty(message = "The last name can not be empty")
        String lastName,

        @NotNull(message = "The first name can not be empty")
        String phone,

        @Email
        String email,

        @NotNull(message = "The DNI can not be null")
        @Size(min = 6, message = "The DNI must be at least 6 digits")
        String DNI
) {}
