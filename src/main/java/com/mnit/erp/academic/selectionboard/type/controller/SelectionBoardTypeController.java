package com.mnit.erp.academic.selectionboard.type.controller;

import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import com.mnit.erp.academic.selectionboard.type.service.SelectionBoardTypeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/selectionBoardType")
public class SelectionBoardTypeController {

    @Autowired
    SelectionBoardTypeService selectionBoardTypeService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody SelectionBoardType selectionBoardType){
        SelectionBoardType selectionBoardType1 = this.selectionBoardTypeService.add(selectionBoardType);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoardType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoardType1) ? "Selection Board Sub-Type added successfully in masters!" : "Unable to add selection board Sub-Type in masters!")
                .messageType(responseMessageType)
                .response(selectionBoardType1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody SelectionBoardType selectionBoardType){
        SelectionBoardType selectionBoardType1 = this.selectionBoardTypeService.update(selectionBoardType);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoardType1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoardType1) ? "Selection Board Sub-Type updated successfully in masters!" : "Unable to update selection board Sub-Type in masters!")
                .messageType(responseMessageType)
                .response(selectionBoardType1).build();
    }

    @GetMapping("/find/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        SelectionBoardType selectionBoardType = this.selectionBoardTypeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoardType) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoardType) ? "Selection Board sub-type found successfully in masters!" : "Unable to find selection board sub-type in masters!")
                .messageType(responseMessageType)
                .response(selectionBoardType).build();
    }

    @GetMapping("/findUnderSelectionBoard/{selectionBoardId}")
    public CustomResponseMessage findAllUnderSelectionBoard(@PathVariable Long selectionBoardId){
        List<SelectionBoardType> allUnderSelectionBoard = this.selectionBoardTypeService.findAllUnderSelectionBoard(selectionBoardId);
        ResponseMessageType responseMessageType = Objects.nonNull(allUnderSelectionBoard) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(allUnderSelectionBoard) && allUnderSelectionBoard.size() > 0 ? "Selection Board sub-types found under selection board successfully in masters!" : "Unable to find selection board sub-type under selection board in masters!")
                .messageType(responseMessageType)
                .response(allUnderSelectionBoard).build();
    }

}
