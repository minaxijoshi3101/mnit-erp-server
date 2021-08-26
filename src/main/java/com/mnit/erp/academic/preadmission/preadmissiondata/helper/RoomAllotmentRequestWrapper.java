package com.mnit.erp.academic.preadmission.preadmissiondata.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAllotmentRequestWrapper {

    private Long specializationId;
    private Date fromDate;
    private Date toDate;
    private List<AllotRoom> rooms;

    @JsonCreator
    public RoomAllotmentRequestWrapper(List<AllotRoom> rooms, Long specializationId, Date fromDate, Date toDate) {
        this.rooms = rooms;
        this.specializationId = specializationId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public static class AllotRoom {
        private final Long id;
        private final Long capacity;

        @JsonCreator
        public AllotRoom(Long id, Long capacity) {
            this.id = id;
            this.capacity = capacity;
        }

        public Long getId() {
            return id;
        }

        public Long getCapacity() {
            return capacity;
        }

    }
}
