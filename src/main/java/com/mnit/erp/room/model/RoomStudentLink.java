package com.mnit.erp.room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomStudentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long preadmissiondata_id;
    Long roomallotment_id;
    Long student_id;

}
