package com.mnit.erp.room.repository;

import com.mnit.erp.location.model.Location;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.room.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Contains Room repository
 *
 * @author: Tejpal Singh
 * @date: 17 June, 2021
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByNameAndRoomTypeAndLocation(String name, RoomType roomType, Location location);
}
