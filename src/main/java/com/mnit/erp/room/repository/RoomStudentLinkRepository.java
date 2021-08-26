package com.mnit.erp.room.repository;

import com.mnit.erp.room.model.RoomStudentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStudentLinkRepository extends JpaRepository<RoomStudentLink, Long> {

}
