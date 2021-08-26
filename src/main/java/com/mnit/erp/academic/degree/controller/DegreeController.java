package com.mnit.erp.academic.degree.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.degree.service.DegreeService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/degree")
public class DegreeController extends AbstractController {

    @Autowired
    DegreeService degreeService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Degree degree){
        Degree degree1 = this.degreeService.add(degree);
        ResponseMessageType responseMessageType = Objects.nonNull(degree1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(degree1) ? "Degree added successfully in masters!" : "Unable to add degree in masters!")
                .messageType(responseMessageType)
                .response(degree1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Degree degree){
        Degree degree1 = this.degreeService.update(degree);
        ResponseMessageType responseMessageType = Objects.nonNull(degree1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(degree1) ? "Degree updated successfully in masters!" : "Unable to update degree in masters!")
                .messageType(responseMessageType)
                .response(degree1).build();
    }


    @GetMapping("/findAllUnderProgram/{programId}")
    public CustomResponseMessage findAllUnderProgram(@PathVariable Long programId){
        List<Degree> degrees = this.degreeService.findAllUnderProgram(programId);
        ResponseMessageType responseMessageType = Objects.nonNull(degrees) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(degrees) ? "Degrees under program loaded successfully!" : "Unable to load degree data under program from masters masters!")
                .messageType(responseMessageType)
                .response(degrees).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Degree degree1 = this.degreeService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(degree1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(degree1) ? "Degree found successfully in masters!" : "Unable to find degree in masters!")
                .messageType(responseMessageType)
                .response(degree1).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Degree> degrees = this.degreeService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(degrees) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(degrees) ? "Degree data loaded successfully!" : "Unable to load degree data from masters masters!")
                .messageType(responseMessageType)
                .response(degrees).build();
    }

}
