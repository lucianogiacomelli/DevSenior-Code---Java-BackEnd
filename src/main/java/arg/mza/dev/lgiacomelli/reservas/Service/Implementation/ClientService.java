package arg.mza.dev.lgiacomelli.reservas.Service.Implementation;

import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceAlreadyExistsException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceInvalidException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceNotFoundException;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.ClientRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;
import arg.mza.dev.lgiacomelli.reservas.Repository.ClientRepository;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IClientService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private ClientRepository clientRepository;

    public ClientService (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    @Transactional
    public Client clientRegistration(ClientRequest clientRequest) {
        if  (clientRepository.findByDni(clientRequest.DNI()).isPresent()){
            throw new ResourceAlreadyExistsException("The client with DNI:"+clientRequest.DNI()+" already exist.");
        };
        Client client = new Client();
        client.setDni(clientRequest.DNI());
        client.setPhone(clientRequest.phone());
        client.setEmail(clientRequest.email());
        client.setFirstName(clientRequest.firtName());
        client.setLastName(clientRequest.lastName());
        client.setCreatedAt(LocalDateTime.now());
        return clientRepository.save(client);
    }
    @Transactional(readOnly = true)
    @Override
    public Client getClientById(Long id) {
        Optional <Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()){
            throw new ResourceNotFoundException("The client with ID: "+id+" does not exist in the DB. ");
        }
        Client client = clientOptional.get();
        return client;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Client> getAllClients() {
        List <Client> clients = clientRepository.findAll();
        if(clients.isEmpty()){
            throw new ResourceNotFoundException("No client exist in the DB.");
        }
        return clients;
    }

    @Transactional
    @Override
    public Client deleteClient(Long clientId){
        Optional <Client> clientOptional = clientRepository.findById(clientId);
        if(clientOptional.isEmpty()){
            throw new ResourceNotFoundException("The client with ID: "+clientId+" does not exist in the DB. ");
        }
        Client client = clientOptional.get();
        if(!client.getEstado()){
            throw new ResourceInvalidException("The client with ID"+clientId+" is already deactivated.");
        }
        client.setEstado(false);
        client.setDeletedAt(LocalDateTime.now());
    }
}
