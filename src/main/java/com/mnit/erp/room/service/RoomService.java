package com.mnit.erp.room.service;

import com.mnit.erp.room.model.Room;

import java.util.List;
/**
 * Contains Room service
 *
 * @author: Tejpal Singh
 * @date: 17 June, 2021
 */
public interface RoomService {

    Room add(Room room);

    Room update(Room room);

    Room find(Long id);

    List<Room> findAll();
}
