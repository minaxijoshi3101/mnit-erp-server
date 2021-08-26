package com.mnit.erp.room.model;

import com.mnit.erp.location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
/**
 * Contains Room model
 *
 * @author: Tejpal Singh
 * @date: 17 June, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    RoomType roomType;
    Long capacity;
    Boolean status;
    Date addedOn;
    Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name="location_id_fk"))
    private Location location;

    public Room(Long roomId) {
        this.id = roomId;
    }

    @Transient
    Long locationId;
    public void setLocationId(Long locationId){
        this.location = Objects.nonNull(locationId) ? new Location(locationId) : null;
    }

    public Long getLocationId(){
        return Objects.nonNull(this.location) ? this.location.getId() : null;
    }

}
