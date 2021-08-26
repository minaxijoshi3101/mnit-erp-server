package com.mnit.erp.room.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.room.model.Room;
import com.mnit.erp.room.service.RoomService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
/**
 * Contains Room controller
 *
 * @author: Tejpal Singh
 * @date: 17 June, 2021
 */
@RestController
@RequestMapping("/room")
public class RoomController extends AbstractController {

    @Autowired
    RoomService roomService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Room room){
        Room room1 = this.roomService.add(room);
        ResponseMessageType responseMessageType = Objects.nonNull(room1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(room1) ? "Room added successfully in masters!" : "Unable to add room in masters!")
                .messageType(responseMessageType)
                .response(room1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Room room){
        Room room1 = this.roomService.update(room);
        ResponseMessageType responseMessageType = Objects.nonNull(room1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(room1) ? "Room updated successfully in masters!" : "Unable to update room in masters!")
                .messageType(responseMessageType)
                .response(room1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Room room1 = this.roomService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(room1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(room1) ? "Room found successfully in masters!" : "Unable to find room in masters!")
                .messageType(responseMessageType)
                .response(room1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Room> rooms = this.roomService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(rooms) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(rooms) ? "Room data loaded successfully!" : "Unable to load room data from masters masters!")
                .messageType(responseMessageType)
                .response(rooms).build();
    }

}
