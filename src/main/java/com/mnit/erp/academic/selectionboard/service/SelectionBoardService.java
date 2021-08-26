package com.mnit.erp.academic.selectionboard.service;

import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.repository.SelectionBoardRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SelectionBoardService {

    @Autowired
    SelectionBoardRepository selectionBoardRepository;

    private boolean validate(SelectionBoard selectionBoard){
        return true;
    }

    public SelectionBoard add(SelectionBoard selectionBoard){
        if(this.validate(selectionBoard)){
            return this.selectionBoardRepository.save(selectionBoard);
        }
        return null;
    }

    public SelectionBoard update(SelectionBoard selectionBoard){
        SelectionBoard selectionBoard1 = this.selectionBoardRepository.findById(selectionBoard.getId()).orElse(null);
        if(Objects.isNull(selectionBoard1)){
            throw new ServiceException("Selection Board not found. Can't updated");
        }
        if(this.validate(selectionBoard)){
            CommonUtils.copyNonNullProperties(selectionBoard, selectionBoard1);
            return this.selectionBoardRepository.save(selectionBoard);
        }
        return null;
    }

    public SelectionBoard find(Long id){
        return  this.selectionBoardRepository.findById(id).orElse(null);
    }

    public List<SelectionBoard> findAll(){
        return  this.selectionBoardRepository.findAll();
    }

}
