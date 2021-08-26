package com.mnit.erp.academic.selectionboard.type.service;

import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import com.mnit.erp.academic.selectionboard.type.repository.SelectionBoardTypeRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SelectionBoardTypeService {

    @Autowired
    SelectionBoardTypeRepository selectionBoardTypeRepository;


    public SelectionBoardType add(SelectionBoardType selectionBoardType) {
        SelectionBoardType byName = this.selectionBoardTypeRepository.findByName(selectionBoardType.getName());
        if(Objects.nonNull(byName)){
            throw new ServiceException("Selection Board Type : " + selectionBoardType.getName() + " already exits. Can't add new!");
        }
        if(this.validate(selectionBoardType)){
            return  this.selectionBoardTypeRepository.save(selectionBoardType);
        }
        return null;
    }

    public SelectionBoardType update(SelectionBoardType selectionBoardType) {
        SelectionBoardType selectionBoardType1 = this.selectionBoardTypeRepository.findById(selectionBoardType.getId()).orElse(null);
        if(Objects.isNull(selectionBoardType1)){
            throw new ServiceException("Selection board not found in masters. Can't update!");
        }
        if(this.validate(selectionBoardType)){
            CommonUtils.copyNonNullProperties(selectionBoardType, selectionBoardType1);
            return  this.selectionBoardTypeRepository.save(selectionBoardType);
        }
        return null;
    }

    public SelectionBoardType find(Long id){
        return  this.selectionBoardTypeRepository.findById(id).orElse(null);
    }

    public List<SelectionBoardType> findAllUnderSelectionBoard(Long selectionBoardId) {
        return this.selectionBoardTypeRepository.findAllUnderSelectionBoardId(selectionBoardId);
    }

    private boolean validate(SelectionBoardType selectionBoardType){
        return true;
    }

    public List<SelectionBoardType> findByIdIn(List<Long> ids) {
        return this.selectionBoardTypeRepository.findByIdIn(ids);
    }
}
