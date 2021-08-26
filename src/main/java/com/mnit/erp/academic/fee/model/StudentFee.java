package com.mnit.erp.academic.fee.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class StudentFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "feestructure")
    List<StudentFeeStructure> studentFeeStructures;

    Long feestructure_id;
    Long transaction_id;
    Long student_id;
    Double total_amount;
    String transaction_status;

}
