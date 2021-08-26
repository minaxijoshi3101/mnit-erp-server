package com.mnit.erp.room.service;

import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.location.repository.LocationRepository;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.room.repository.RoomRepository;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
/**
 * Contains Room service implementation
 *
 * @author: Tejpal Singh
 * @date: 17 June, 2021
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Room add(Room room) {
        Room room1 = this.roomRepository.findByNameAndRoomTypeAndLocation(room.getName(), room.getRoomType(), room.getLocation());
        if(Objects.nonNull(room1)){
            throw new ServiceException("Room : " + room.getName() + " of type: "+room.getRoomType()+" at select location already exists. Can't add");
        }
        if(this.validate(room))
            return this.roomRepository.save(room);
        return null;
    }

    @Override
    public Room update(Room room) {
        Room savedRoom = this.roomRepository.findById(room.getId()).orElse(null);
        if(Objects.isNull(savedRoom)){
            throw new ServiceException("Room not found. Hence can't update!");
        }
        if(this.validate(room)) {
            CommonUtils.copyNonNullProperties(room, savedRoom);
            return this.roomRepository.save(savedRoom);
        }
        return null;
    }

    @Override
    public Room find(Long id) {
        return this.roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    private boolean validate(Room room){
        return true;
    }

}
