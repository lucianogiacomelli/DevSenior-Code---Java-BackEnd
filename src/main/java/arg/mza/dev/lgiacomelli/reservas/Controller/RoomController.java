package arg.mza.dev.lgiacomelli.reservas.Controller;

import arg.mza.dev.lgiacomelli.reservas.Mapper.RoomMapper;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.RoomRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.CompleteRoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.RoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;


    @GetMapping("/")
    public ResponseEntity<?> getAllRooms(){
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> response = rooms.stream().map(RoomMapper::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable Long roomId){
        CompleteRoomResponse room = roomService.getRoomById(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@Valid @RequestBody RoomRequest roomRequest){
        Room room = roomService.createRoom(roomRequest);
        RoomResponse response = RoomMapper.toResponse(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
