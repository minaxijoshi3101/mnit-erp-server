package com.mnit.erp.holiday.repository;

import com.mnit.erp.holiday.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query( nativeQuery = true, value = "SELECT * FROM holiday WHERE year(holidayDate)= ?1 ")
    List<Holiday> findAllInYear(Long year);

    @Query( nativeQuery = true, value = "SELECT * FROM holiday WHERE holidayDate = ?1 ")
    List<Holiday> findAllOnDate(Date date);

}
