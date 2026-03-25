package arg.mza.dev.lgiacomelli.reservas.Repository;


import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> getRoomByRoomNumberAndFloorNumber(Integer roomNumber, Integer floorNumber);
}
