package arg.mza.dev.lgiacomelli.reservas.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client extends Base {

    private String firstName;
    private String lastName;
    private Integer phone;
    private Integer dni;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Booking> bookings;





}
