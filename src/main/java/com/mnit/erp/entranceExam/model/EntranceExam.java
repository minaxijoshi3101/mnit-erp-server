package com.mnit.erp.entranceExam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EntranceExam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    EntranceExamType examType;

    String examYear;
    String rollNo;
    Double score;
    Long examRank;  // GLOBAL RANK


}
