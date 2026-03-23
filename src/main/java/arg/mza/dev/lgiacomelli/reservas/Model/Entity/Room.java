package arg.mza.dev.lgiacomelli.reservas.Model.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Room extends Base{
    private Integer roomNumber;
    private Integer floorNumber;
    private Integer size;
    private RoomType roomType;


    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

}
