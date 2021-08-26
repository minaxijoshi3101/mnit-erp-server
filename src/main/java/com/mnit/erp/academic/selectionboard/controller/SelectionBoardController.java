package com.mnit.erp.academic.selectionboard.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.selectionboard.service.SelectionBoardService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/selectionBoard")
public class SelectionBoardController extends AbstractController {

    @Autowired
    SelectionBoardService selectionBoardService;

    @PostMapping("/add")
    public CustomResponseMessage save(@RequestBody SelectionBoard selectionBoard){
        SelectionBoard selectionBoard1 = this.selectionBoardService.add(selectionBoard);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoard1) ? "Selection Board added successfully in masters!" : "Unable to add selection board in masters!")
                .messageType(responseMessageType)
                .response(selectionBoard1).build();
    }

    @PutMapping("/update")
    public CustomResponseMessage update(@RequestBody SelectionBoard selectionBoard){
        SelectionBoard selectionBoard1 = this.selectionBoardService.update(selectionBoard);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoard1) ? "Selection Board updated successfully in masters!" : "Unable to update selection board in masters!")
                .messageType(responseMessageType)
                .response(selectionBoard1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable  Long id){
        SelectionBoard selectionBoard1 = this.selectionBoardService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoard1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoard1) ? "Selection Board found successfully in masters!" : "Unable to find selection board in masters!")
                .messageType(responseMessageType)
                .response(selectionBoard1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<SelectionBoard> selectionBoards = this.selectionBoardService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(selectionBoards) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(selectionBoards) && selectionBoards.size() > 0 ? "Selection Boards found successfully in masters!" : "Unable to find selection boards in masters!")
                .messageType(responseMessageType)
                .response(selectionBoards).build();
    }

}
