package com.mnit.erp.room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomAllotment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date allotmentFromdate;
    Date allotmentTodate;
    Date createdOn;
    Date updatedOn;
    Long room_id;

}
