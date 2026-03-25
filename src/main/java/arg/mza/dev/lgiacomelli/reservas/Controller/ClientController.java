package arg.mza.dev.lgiacomelli.reservas.Controller;


import arg.mza.dev.lgiacomelli.reservas.Mapper.ClientMapper;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.ClientRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.ClientResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;
import arg.mza.dev.lgiacomelli.reservas.Service.Implementation.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @PostMapping("/registration")
    public ResponseEntity<?> clientRegistration(@Valid @RequestBody ClientRequest clientRequest){
        Client client = clientService.clientRegistration(clientRequest);
        ClientResponse response = ClientMapper.toResponse(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById (@PathVariable Long clientId){
        Client client = clientService.getClientById(clientId);
        ClientResponse response = ClientMapper.toResponse(client);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClients (){
        List<Client> clients = clientService.getAllClients();
        List<ClientResponse> response = clients.stream().map(ClientMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
