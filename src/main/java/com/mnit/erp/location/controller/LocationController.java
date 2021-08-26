package com.mnit.erp.location.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.location.model.Location;
import com.mnit.erp.location.service.LocationService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Contains Location rest api controller
 *
 * @author: Tejpal Singh
 * @return: add/update/find/findAll APIs
 * @date: 16 June, 2021
 */
@RestController
@RequestMapping("/location")
public class LocationController extends AbstractController {

    @Autowired
    LocationService locationService;

    @PostMapping("/add")
    public CustomResponseMessage add(@RequestBody Location location){
        Location location1 = this.locationService.add(location);
        ResponseMessageType responseMessageType = Objects.nonNull(location1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(location1) ? "Location added successfully in masters!" : "Unable to add location in masters!")
                .messageType(responseMessageType)
                .response(location1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody Location location){
        Location location1 = this.locationService.update(location);
        ResponseMessageType responseMessageType = Objects.nonNull(location1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(location1) ? "Location updated successfully in masters!" : "Unable to update location in masters!")
                .messageType(responseMessageType)
                .response(location1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Location location1 = this.locationService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(location1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(location1) ? "Location found successfully in masters!" : "Unable to find location in masters!")
                .messageType(responseMessageType)
                .response(location1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Location> locations = this.locationService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(locations) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(locations) && !locations.isEmpty() ? "Locations found successfully in masters!" : "Unable to find locations in masters!")
                .messageType(responseMessageType)
                .response(locations).build();
    }

}
