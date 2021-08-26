package com.mnit.erp.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mnit.erp.common.model.SlotOpen;

@Repository
public interface SlotOpenRepository extends JpaRepository<SlotOpen, Long> {
	@Query("select c from SlotOpen c where (c.program = :#{#cp.program} or :#{#cp.program} is null) And "
			 + " (c.degree = :#{#cp.degree} or :#{#cp.degree} is null) And "
			 + " (c.department = :#{#cp.department} or :#{#cp.department} is null) And "
			 + " (c.semester = :#{#cp.semester} or :#{#cp.semester} is null) And "
			 + " (c.acYear = :#{#cp.acYear} or :#{#cp.acYear} is null) And "
			 + " (c.activity = :#{#cp.activity} or :#{#cp.activity} is null) And "
			 + " (c.fromDate = :#{#cp.fromDate} or :#{#cp.fromDate} is null) And "
			 + " (c.toDate = :#{#cp.toDate} or :#{#cp.toDate} is null) And "
			 + " (c.status = :#{#cp.status} or :#{#cp.status} is null) " )
		List<SlotOpen> findAsParam(@Param("cp") SlotOpen cp);
}
