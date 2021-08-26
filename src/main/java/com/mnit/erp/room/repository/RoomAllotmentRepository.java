package com.mnit.erp.room.repository;

import com.mnit.erp.room.model.RoomAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAllotmentRepository extends JpaRepository<RoomAllotment, Long> {

}
