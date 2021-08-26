package com.mnit.erp.academic.fee.repository;

import com.mnit.erp.academic.fee.model.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePaymentRepository extends JpaRepository<StudentFee, Long> {

}
