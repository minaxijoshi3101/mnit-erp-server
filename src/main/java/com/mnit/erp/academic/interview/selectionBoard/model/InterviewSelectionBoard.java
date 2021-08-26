package com.mnit.erp.academic.interview.selectionBoard.model;

import com.mnit.erp.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

/**
 * Contains Interview selection board model and its dependencies
 *
 * @author: Tejpal Singh
 * @return: Object of Interview selection board model
 * @date: 28 July, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterviewSelectionBoard extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 128)
    String name;
    @Column(nullable = false)
    Date meetingDate;
    @Column(nullable = false, length = 20)
    String meetingNumber;
    String momFilePath;
    @Transient
    MultipartFile momFile;
    Boolean status;

    public InterviewSelectionBoard(Long interviewSelectionBoardId) {
        this.id = interviewSelectionBoardId;
    }
}