package arg.mza.dev.lgiacomelli.reservas.Repository;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.ClientRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDni(Integer Dni);

}
