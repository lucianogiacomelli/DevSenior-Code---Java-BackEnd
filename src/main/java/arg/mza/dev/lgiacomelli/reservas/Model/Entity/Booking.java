package arg.mza.dev.lgiacomelli.reservas.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking extends Base{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Room room;
}
