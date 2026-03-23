package arg.mza.dev.lgiacomelli.reservas.Model.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity

public class Room extends Base{
    private Integer roomNumber;
    private Integer floorNumber;
    private Integer size;
    private RoomType roomType;


    @OneToMany(mappedBy = "room")
    private Booking booking;

}
