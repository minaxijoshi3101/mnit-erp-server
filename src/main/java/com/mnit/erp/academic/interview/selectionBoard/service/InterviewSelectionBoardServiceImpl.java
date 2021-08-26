package com.mnit.erp.academic.interview.selectionBoard.service;

import com.mnit.erp.academic.course.model.Course;
import com.mnit.erp.academic.interview.selectionBoard.model.InterviewSelectionBoard;
import com.mnit.erp.academic.interview.selectionBoard.repository.InterviewSelectionBoardRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * Contains Interview selection board Service Implementation
 *
 * @author: Tejpal Singh
 * @definition: add/update/find/findAll
 * @date: 28 July, 2021
 */
@Service
@Slf4j
public class InterviewSelectionBoardServiceImpl implements InterviewSelectionBoardService {

    @Autowired
    InterviewSelectionBoardRepository interviewSelectionBoardRepository;

    @Override
    public InterviewSelectionBoard add(InterviewSelectionBoard interviewSelectionBoard) {

        InterviewSelectionBoard byName = this.interviewSelectionBoardRepository.findByName(interviewSelectionBoard.getName());
        if (Objects.nonNull(byName)) {
            throw new ServiceException("Interview selection board for this student already exists. Can't add again!");
        }
        if (this.validate(interviewSelectionBoard)){
            return this.interviewSelectionBoardRepository.save(interviewSelectionBoard);
        }
        return null;
    }

    @Override
    public InterviewSelectionBoard update(InterviewSelectionBoard interviewSelectionBoard) {
        InterviewSelectionBoard byNameAndIdNot = this.interviewSelectionBoardRepository.findByNameAndIdNot( interviewSelectionBoard.getName(), interviewSelectionBoard.getId());
        if(Objects.nonNull(byNameAndIdNot)){
            throw new ServiceException("Interview selection board for this student already exists. Can't update!");
        }

        InterviewSelectionBoard savedInterviewSelectionBoard = this.interviewSelectionBoardRepository.findById(interviewSelectionBoard.getId()).orElse(null);
        if(Objects.isNull(savedInterviewSelectionBoard)){
            throw new ServiceException("Interview selection board not found. Can't update!");
        }
        if(this.validate(interviewSelectionBoard)) {
            CommonUtils.copyNonNullProperties(interviewSelectionBoard, savedInterviewSelectionBoard);
            return this.interviewSelectionBoardRepository.save(interviewSelectionBoard);
        }
        return null;
    }

    @Override
    public InterviewSelectionBoard find(Long id) {
        return this.interviewSelectionBoardRepository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewSelectionBoard> findAll() {
        return this.interviewSelectionBoardRepository.findAll();
    }

    public CustomResponseMessage uploadFile(Long interviewSelectionBoardId, MultipartFile momFile){
        String sActionRes = "File uploaded failed";
        InterviewSelectionBoard interviewSelectionBoardEx = null;
        try{
            if(interviewSelectionBoardId > 0 && momFile.getBytes().length > 0){
                interviewSelectionBoardEx = interviewSelectionBoardRepository.findById(interviewSelectionBoardId).orElse(null);
                if(interviewSelectionBoardEx != null) {
                    //if interview selection board mom file has values then save the file
                    interviewSelectionBoardEx.setMomFilePath(CommonUtils.saveFile("interview/sb", momFile));
                    interviewSelectionBoardRepository.save(interviewSelectionBoardEx);
                    sActionRes = "File uploaded successfuly";
                }
            }
        }catch(Exception ex){
            log.error("",ex);
            interviewSelectionBoardEx = null;
            sActionRes = "Unable to upload file";
        }
        return CommonUtils.buildResponse(interviewSelectionBoardEx, sActionRes);
    }
    private boolean validate(InterviewSelectionBoard interviewSelectionBoard){
        return true;
    }

}
