package arg.mza.dev.lgiacomelli.reservas.Service.Implementation;

import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceAlreadyExistsException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceNotFoundException;
import arg.mza.dev.lgiacomelli.reservas.Mapper.BookingMapper;
import arg.mza.dev.lgiacomelli.reservas.Mapper.CompleteRoomMapper;
import arg.mza.dev.lgiacomelli.reservas.Mapper.RoomMapper;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Request.RoomRequest;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.BookingResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Dto.Response.CompleteRoomResponse;
import arg.mza.dev.lgiacomelli.reservas.Model.Entity.Room;
import arg.mza.dev.lgiacomelli.reservas.Repository.RoomRepository;
import arg.mza.dev.lgiacomelli.reservas.Service.Interface.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomService (RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public CompleteRoomResponse getRoomById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if(roomOptional.isEmpty()){
            throw new ResourceNotFoundException("The room with ID:"+id+" does not exist in the DB");
        }
        Room room = roomOptional.get();
        List<BookingResponse> bookingResponseList = room.getBookings().stream().
                filter(b -> !b.getCheckInDate().isBefore(LocalDate.now())).
                map(BookingMapper::toResponse).toList();
        return CompleteRoomMapper.toResponse(room, bookingResponseList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Room> getAllRooms() {
        List <Room> rooms = roomRepository.findAll();
        if(rooms.isEmpty()){
            throw new ResourceNotFoundException("There are no rooms in the DB");
        }
        return rooms;
    }

    @Transactional
    @Override
    public Room createRoom(RoomRequest roomRequest) {
        if(roomRepository.getRoomByRoomNumberAndFloorNumber(roomRequest.roomNumber(), roomRequest.floorNumber()).isEmpty()){
            throw new ResourceAlreadyExistsException
                    ("The room in the "+roomRequest.floorNumber()+"th floor, number: "+roomRequest.roomNumber()+" already exist.");
        }
        Room room = new Room();
        room.setRoomNumber(roomRequest.roomNumber());
        room.setRoomType(roomRequest.roomType());
        room.setFloorNumber(roomRequest.floorNumber());
        return roomRepository.save(room);
    }

}
