package arg.mza.dev.lgiacomelli.reservas.Mapper;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.ClientResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;

public class ClientMapper {

    public static ClientResponse toResponse(Client c) {
        return new ClientResponse(
                c.getId(),
                c.getCreatedAt(),
                c.getDeletedAt(),
                c.getEstado(),
                c.getFirstName(),
                c.getLastName(),
                c.getPhone(),
                c.getEmail(),
                c.getDni()
        );
    }
}