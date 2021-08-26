package com.mnit.erp.academic.program.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.program.model.Program;
import com.mnit.erp.academic.program.service.ProgramService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/program")
public class ProgramController extends AbstractController {

    @Autowired
    ProgramService programService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Program program){
        Program program1 = this.programService.add(program);
        ResponseMessageType responseMessageType = Objects.nonNull(program1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(program1) ? "Program added successfully in masters!" : "Unable to add program in masters!")
                .messageType(responseMessageType)
                .response(program1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Program program){
        Program program1 = this.programService.update(program);
        ResponseMessageType responseMessageType = Objects.nonNull(program1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(program1) ? "Program updated successfully in masters!" : "Unable to update program in masters!")
                .messageType(responseMessageType)
                .response(program1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Program program1 = this.programService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(program1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(program1) ? "Program found successfully in masters!" : "Unable to find program in masters!")
                .messageType(responseMessageType)
                .response(program1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Program> programs = this.programService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(programs) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(programs) && !programs.isEmpty() ? "Programs found successfully in masters!" : "Unable to find programs in masters!")
                .messageType(responseMessageType)
                .response(programs).build();
    }

}
