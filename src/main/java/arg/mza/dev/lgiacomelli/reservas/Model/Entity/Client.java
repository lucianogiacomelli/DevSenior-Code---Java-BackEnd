package arg.mza.dev.lgiacomelli.reservas.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Base {

    private String firstName;
    private String lastName;
    private Integer phone;
    private Integer DNI;
    private String email;

    @OneToMany(mappedBy = "client")
    private Booking booking;





}
