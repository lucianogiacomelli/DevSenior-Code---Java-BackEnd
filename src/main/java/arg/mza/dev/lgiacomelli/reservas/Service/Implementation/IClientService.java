package arg.mza.dev.lgiacomelli.reservas.Service.Implementation;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.ClientRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClientService {
    Client clientRegistration (ClientRequest clientRequest);
    Client getClientById (Long id);
    List<Client> getAllClients();


}
