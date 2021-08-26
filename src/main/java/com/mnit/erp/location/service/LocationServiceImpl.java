package com.mnit.erp.location.service;

import com.mnit.erp.location.model.Location;
import com.mnit.erp.location.repository.LocationRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Contains Location Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 16 June, 2021
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Location add(Location location) {
        Location byName = locationRepository.findByNameAndLocationType(location.getName(),location.getLocationType());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Location:" + location.getName() + ", Location Type: "+location.getLocationType()+" already exists. Can't add again!");
        }
        if(this.validate(location))
            return this.locationRepository.save(location);
        return null;
    }

    @Override
    public Location update(Location location) {
        Location savedLocation = this.locationRepository.findById(location.getId()).orElse(null);
        if(Objects.isNull(savedLocation)){
            throw new ServiceException("Location not found. Can't update!");
        }
        if(this.validate(location)) {
            CommonUtils.copyNonNullProperties(location, savedLocation);
            return this.locationRepository.save(location);
        }
        return null;
    }

    @Override
    public Location find(Long id) {
        return this.locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    private boolean validate(Location location){
        return true;
    }

}
