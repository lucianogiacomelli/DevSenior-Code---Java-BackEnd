package arg.mza.dev.lgiacomelli.reservas.Service.Interface;

import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.RoomRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.CompleteRoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoomService {

    CompleteRoomResponse getRoomById(Long id);
    List<Room> getAllRooms();
    Room createRoom(RoomRequest roomRequest);

}
